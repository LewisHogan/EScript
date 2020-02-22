import escript.ast.ASTVisitor;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import escript.ast.nodes.StartNode;
import escript.ast.nodes.condition.BranchNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.condition.IfNode;
import escript.ast.nodes.statement.*;
import escript.ast.nodes.statement.payload.ForPayload;
import escript.ast.nodes.statement.payload.WhilePayload;
import escript.ast.nodes.values.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms Escript into functional Python code.
 */
public class OldPythonVisitor extends ASTVisitor<String> {

    private int indentationLevel = 0;

    private String indent() {
        String out = "";
        for (int i = 0; i < indentationLevel; i++)
            out += "\t";
        return out;
    }

    @Override
    protected String visitStart(StartNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            output += visit((ASTNode) node.getChild(i));
            if (i != node.getChildCount()-1) output += "\n";
        }
        return output;
    }

    @Override
    protected String visitBranch(BranchNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            if (i == 0) output = visit((ASTNode) node.getChild(0));
            else output += indent() + visit((ASTNode) node.getChild(i));
            if (i != node.getChildCount()-1) output += "\n";
        }

        return output;
    }

    @Override
    protected String visitCondition(ConditionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String symbol = "";
        switch ((EComparisonOperator) node.getPayload()) {
            case EQUALS:
                symbol = "==";
                break;
            case NOT_EQUALS:
                symbol = "!=";
                break;
            case OR:
                symbol = "or";
                break;
            case AND:
                symbol = "and";
                break;
            case LESS_THAN:
                symbol = "<";
                break;
            case LESS_THAN_OR_EQUALS:
                symbol = "<=";
                break;
            case GREATER_THAN:
                symbol = ">";
                break;
            case GREATER_THAN_OR_EQUALS:
                symbol = ">=";
                break;
        }

        if (node.getChildCount() == 1) return String.format("%s", visit((ASTNode) node.getChild(0)));
        return String.format("%s %s %s", visit((ASTNode) node.getChild(0)), node.getPayload(), visit((ASTNode) node.getChild(1)));
    }

    @Override
    protected String visitIf(IfNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        boolean isElseIf = node.getParent().getChild(0) != node;
        String conditional = String.format("%sif %s:\n", isElseIf ? "el" : "", visit((ConditionNode) node.getPayload()));
        String body = "";
        indentationLevel++;
        for (int i = 0; i < node.getChildCount(); i++) {
            body += indent() + visit((ASTNode) node.getChild(i));
//            body += visit((ASTNode) node.getChild(i));
            if (i != node.getChildCount()-1) body += "\n";
        }
        indentationLevel--;

        return conditional + body;
    }

    @Override
    protected String visitAssignment(AssignmentNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("%s = %s", visit((ASTNode) node.getChild(0)), visit((ASTNode) node.getChild(1)));
    }

    @Override
    protected String visitExpression(ExpressionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String expressionSymbol = "";
        switch ((EExpressionOperator) node.getPayload()) {
            case ADD:
                expressionSymbol = "+";
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
        return String.format("%s %s %s", visit((ASTNode) node.getChild(0)), node.getPayload(), (ASTNode) node.getChild(1));
    }

    @Override
    protected String visitFor(ForNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        // Transform into a while structure, since python for loops are weird
//        ForPayload forPayload = (ForPayload) node.getPayload();
//
//        List<ASTNode> statements = new ArrayList<>();
//        String firstBeforeLoop = visit(forPayload.getInitial());
//
//        for (int i = 0; i < node.getChildCount(); i++) {
//            statements.add((ASTNode) node.getChild(i));
//        }
//
//        statements.add(forPayload.getNext());
//        WhileNode whileNode = new WhileNode(
//                forPayload.getCondition(),
//                statements
//        );
//
//        return visit(whileNode);
        return "FOR NOT IMPLEMENTED";
    }

    @Override
    protected String visitInversion(InversionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (node.getPayload().toString().equals("!"))  return String.format("not %s", visit((ASTNode) node.getChild(0)));
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
        indentationLevel++;
        String body = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            body += indent() + visit((ASTNode) node.getChild(i));
            if (i != node.getChildCount()-1) body += "\n";
        }
        indentationLevel--;
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
}
