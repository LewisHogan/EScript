package escript;

import escript.ast.ASTVisitor;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import escript.ast.nodes.StartNode;
import escript.ast.nodes.condition.*;
import escript.ast.nodes.function.FunctionCallNode;
import escript.ast.nodes.function.FunctionDefinitionNode;
import escript.ast.nodes.function.ReturnNode;
import escript.ast.nodes.function.payload.FunctionDefinitionPayload;
import escript.ast.nodes.function.payload.ReturnPayload;
import escript.ast.nodes.statement.*;
import escript.ast.nodes.statement.payload.ForPayload;
import escript.ast.nodes.statement.payload.WhilePayload;
import escript.ast.nodes.values.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transpiles EScript into Python code.
 */
public class PythonCompilerVisitor extends ASTVisitor<String> {

    private int indentationLevel = 0;

    private String indent() {
        String out = "";
        for (int i = 0; i < indentationLevel; i++) {
            out += "\t";
        }
        return out;
    }

    @Override
    protected String visitStart(StartNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        // Remove the trailing new line from the last statement in the program, if it exists
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < node.getChildCount(); i++) {
            String line = visit((ASTNode) node.getChild(i));
            if (i == node.getChildCount() - 1) {
                if (line.endsWith("\n")) line = line.substring(0, line.length() - 1);
            }
            lines.add(line);
        }

        return lines.stream().collect(Collectors.joining("\n"));
    }

    @Override
    protected String visitBranch(BranchNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String output = "";
        ASTNode child = null;
        boolean hasFirstElse = false;

        for (int i = 0; i < node.getChildCount(); i++) {
            child = (ASTNode) node.getChild(i);

            if (child instanceof IfNode) {
                output += visit(child) + "\n";
            } else {
                if (!hasFirstElse) {
                    hasFirstElse = true;
                    output += indent() + "else:\n";
                }
                indentationLevel++;
                output += indent() + visit(child) + "\n";
                indentationLevel--;
            }
        }

        return output;
    }

    @Override
    protected String visitBlock(BlockNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String body = "";
        indentationLevel++;
        for (int i = 0; i < node.getChildCount(); i++) {
            body += indent() + visit((ASTNode) node.getChild(i));
            if (i != node.getChildCount() - 1) body += "\n";
        }
        indentationLevel--;
        return body;
    }

    @Override
    protected String visitCondition(ConditionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String comparisonSymbol = "";
        switch ((EComparisonOperator) node.getPayload()) {
            case EQUALS:
                comparisonSymbol = "==";
                break;
            case NOT_EQUALS:
                comparisonSymbol = "!=";
                break;
            case OR:
                comparisonSymbol = "or";
                break;
            case AND:
                comparisonSymbol = "and";
                break;
            case LESS_THAN:
                comparisonSymbol = "<";
                break;
            case LESS_THAN_OR_EQUALS:
                comparisonSymbol = "<=";
                break;
            case GREATER_THAN:
                comparisonSymbol = ">";
                break;
            case GREATER_THAN_OR_EQUALS:
                comparisonSymbol = ">=";
                break;
        }

        if (node.getChildCount() == 1) return String.format("%s", visit((ASTNode) node.getChild(0)));

        ASTNode left = (ASTNode) node.getChild(0);
        ASTNode right = (ASTNode) node.getChild(1);

        ASTNode parent = (ASTNode) node.getParent();
        if (parent != null && (parent.getPayload() instanceof EComparisonOperator)) {
            // If parent is higher or equal priority, wrap in parenthesis
            if (((EComparisonOperator) parent.getPayload()).isPriority((EComparisonOperator) node.getPayload()))
                return String.format("(%s %s %s)", visit(left), comparisonSymbol, right);
        }
        return String.format("%s %s %s", visit(left), comparisonSymbol, visit(right));
    }

    @Override
    protected String visitIf(IfNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        boolean isElseIf = node.getParent().getChild(0) != node;
        String conditional = String.format("%sif %s:\n", isElseIf ? "el" : "", visit((ConditionNode) node.getPayload()));
        String body = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            // I assume this will break with multistatement ifs
            // I was correct
            // TODO: Check if I fixed this
            ASTNode child = (ASTNode) node.getChild(i);
            if (child instanceof BlockNode)
                body += visit(child);
            else
                body += visit(new BlockNode(Arrays.asList(child)));

            if (i != node.getChildCount() - 1) body += "\n";
        }

        return conditional + body;
    }

    @Override
    protected String visitAssignment(AssignmentNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("%s = %s", visit((ASTNode) node.getChild(0)), visit((ASTNode) node.getChild(1)));
    }

    /**
     * Checks if any nodes in the tree are StringNode
     *
     * @param node The root of the tree to check
     * @return If the node is or contains a StringNode
     */
    private boolean containsString(ASTNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            if (containsString((ASTNode) node.getChild(i))) return true;
        }

        return node instanceof StringNode;
    }

    @Override
    protected String visitExpression(ExpressionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String expressionSymbol = "";
        ASTNode left = (ASTNode) node.getChild(0);
        ASTNode right = (ASTNode) node.getChild(1);
        String leftStr = visit(left);
        String rightStr = visit(right);
        // To handle expressions like ("a" + "b") + "c" we need to recursively check expression nodes
        switch ((EExpressionOperator) node.getPayload()) {
            case ADD:
                expressionSymbol = "+";
                // In python you cannot just add an two non string types for concatenation
                if ((containsString(left)) && !containsString(right)) {
                    rightStr = String.format("str(%s)", rightStr);
                }
                if (!containsString(left) && (containsString(right)))
                    leftStr = String.format("str(%s)", leftStr);
                break;
            case POWER:
                expressionSymbol = "**";
                break;
            case DIVIDE:
                expressionSymbol = "/";
                break;
            case MODULO:
                expressionSymbol = "%";
                break;
            case MULTIPLY:
                expressionSymbol = "*";
                break;
            case SUBTRACT:
                expressionSymbol = "-";
                break;
        }
        ASTNode parent = (ASTNode) node.getParent();
        if (parent != null && (parent.getPayload() instanceof EExpressionOperator)) {
            // If parent is higher or equal priority, wrap in parenthesis
            if (((EExpressionOperator) parent.getPayload()).isPriority((EExpressionOperator) node.getPayload()))
                return String.format("(%s %s %s)", leftStr, expressionSymbol, rightStr);
        }

        return String.format("%s %s %s", leftStr, expressionSymbol, rightStr);
    }

    @Override
    protected String visitFor(ForNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        // Transform for loop into a while loop because that's easier then dealing with weird python for loops
        // which go "for element in set:"
        ForPayload payload = (ForPayload) node.getPayload();

        List<ASTNode> statements = new ArrayList<>();
        for (int i = 0; i < node.getChildCount(); i++)
            statements.add((ASTNode) node.getChild(i));

        statements.add(payload.getNext());

        return visit(payload.getInitial()) + "\n" + indent() + visit(new WhileNode(
                payload.getCondition(),
                statements
        ));
    }

    @Override
    protected String visitInversion(InversionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (node.getPayload().toString().equals("!")) return String.format("not %s", visit((ASTNode) node.getChild(0)));
        return String.format("-%s", visit((ASTNode) node.getChild(0)));
    }

    @Override
    protected String visitPrint(PrintNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("print(%s)", visit((ASTNode) node.getChild(0)));
    }

    @Override
    protected String visitWhile(WhileNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        WhilePayload payload = (WhilePayload) node.getPayload();
        String header = String.format("while %s:\n", visit(payload.getCondition()));
        String body = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            if (child instanceof BlockNode) body += visit(child) + "\n";
            else {
                indentationLevel++;
                body += indent() + visit(child) + "\n";
                indentationLevel--;
            }
        }
        return header + body;
    }

    @Override
    protected String visitBoolean(BooleanNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return ((Boolean) node.getPayload()) ? "True" : "False";
    }

    @Override
    protected String visitFloat(FloatNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitInteger(IntegerNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitString(StringNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitVariable(VariableNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitFunctionDefinitionNode(FunctionDefinitionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        FunctionDefinitionPayload payload = (FunctionDefinitionPayload) node.getPayload();
        String header = String.format("def %s(%s):\n", payload.getFunctionName(),
                payload.getParameters().stream().map(ASTNode::toString).collect(Collectors.joining(", ")));

        String body = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            if (child instanceof BlockNode) body += visit(child) + "\n";
            else {
                indentationLevel++;
                body += indent() + visit(child) + "\n";
                indentationLevel--;
            }
        }
        return header + body;
    }

    @Override
    protected String visitFunctionCall(FunctionCallNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String args = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            args += visit(child) + ((i != node.getChildCount() - 1) ? ", " : "");
        }
        return String.format("%s(%s)", node.getPayload(), args);
    }

    @Override
    protected String visitReturn(ReturnNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("return %s", visit((ASTNode) ((ReturnPayload) node.getPayload()).getPayload()));
    }

    @Override
    protected String visitTernary(TernaryNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("%s if %s else %s", visit((ASTNode) node.getChild(0)), visit((ASTNode) node.getPayload()), visit((ASTNode) node.getChild(1)));
    }
}
