package expressionscript;

import expressionscript.ast.ASTVisitor;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.StartNode;
import expressionscript.ast.nodes.condition.EComparisonOperator;
import expressionscript.ast.nodes.statement.EExpressionOperator;
import expressionscript.ast.nodes.statement.ExpressionNode;
import expressionscript.ast.nodes.values.*;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.IfNode;
import expressionscript.ast.nodes.statement.AssignmentNode;
import org.antlr.v4.runtime.tree.Tree;

public class PrettyPrintVisitor extends ASTVisitor<String> {

    private int indentationLevel = 0;

    private String indent(int n) {
        String output = "";
        for (int i = 0; i < n; i++) output += "\t";
        return output;
    }

    @Override
    public String visitAssignment(AssignmentNode node) {
        return String.format("%s = %s", visit((ASTNode) node.getChild(0)), visit((ASTNode) node.getChild(1)));
    }

    @Override
    public String visitBranch(BranchNode node) {
        // Every branch will at least have a single ifpart aka IfNode
        // they may or may not have an else if or else part
        // the else if parts are the if nodes are the first one
        // The else part is only present if we have any branches that are not ifNodes

        boolean isElsePresent = false;
        int ifNodeCount = 0;
        // Do a pass over the nodes to see if we have any else parts
        for (int i = 0; i < node.getChildCount(); i++) {
            if (!(node.getChild(i) instanceof IfNode)) {
                isElsePresent = true;
                ifNodeCount = i;
                break;
            }
        }

        String output = "";
        for (int i = 0; i < ifNodeCount; i++) {
            output += visit(node.getChild(i));
        }

        if (isElsePresent) {
            // TODO: If there are multiple nodes left, we need to go into another block
            boolean elseHasBlock = node.getChildCount() > 1;

            if (elseHasBlock) {
                String children = "";
                for (int i = ifNodeCount; i < node.getChildCount(); i++) {
                    // If children are not branch or block nodes, we'll need to add a semi colon to the end
                    children += indent(++indentationLevel) + visit(node.getChild(i)) + ";";
                    if (i < node.getChildCount() - 1) children += "\n";
                    indentationLevel--;
                }

                output += String.format("%selse {\n%s\n%s}\n", indent(indentationLevel++), children, indent(--indentationLevel));
            } else {
                output += "else " + visit(node.getChild(node.getChildCount() - 1)) + ";";
            }
        }

        return output;
    }

    @Override
    public String visitCondition(ConditionNode node) {
        String output = String.format("%s %s %s",
                visit(node.getChild(0)), node.getPayload(), visit(node.getChild(1))
        );

        if (node.getParent() instanceof ConditionNode) {
            // If the parent has an operator which is higher priority, we need to print brackets
            boolean needsBrackets = ((EComparisonOperator) node.getParent().getPayload()).
                    isHigherPriority((EComparisonOperator) node.getPayload());

            if (needsBrackets)
                return String.format("(%s)", output);
        }

        return output;
    }

    @Override
    public String visitIf(IfNode node) {

        // If we have multiple statements to one ifNode, when need to put them in braces or we have changed
        // the meaning of the code
        boolean hasStatementBlock = node.getChildCount() > 1;

        // if we are not the first node, we must be an else if node
        boolean isElseIf = node.getParent().getChild(0) != node;

        if (hasStatementBlock) {
            String children = "";
            for (int i = 0; i < node.getChildCount(); i++) {
                // If children are not branch or block nodes, we'll need to add a semi colon to the end
                children += indent(++indentationLevel) + visit(node.getChild(i)) + ";";
                if (i < node.getChildCount() - 1) children += "\n";
                indentationLevel--;
            }

            return String.format("%s%sif (%s) {\n%s\n%s}\n", indent(indentationLevel++), isElseIf ? "else " : "", visit((ASTNode) node.getPayload()), children, indent(--indentationLevel));
        }

        return String.format("%sif (%s) %s\n", isElseIf ? "else " : "",
                visit((ASTNode) node.getPayload()),
                visit(node.getChild(0)) + ";"
        );

    }

    @Override
    public String visitString(StringNode node) {
        // The first and last characters are quote characters
        return ((String) node.getPayload()).substring(1, ((String) node.getPayload()).length() - 1);
    }

    @Override
    public String visitVariable(VariableNode node) {
        return (String) node.getPayload();
    }

    @Override
    public String visitExpression(ExpressionNode node) {
        String output = String.format("%s %s %s", visit(node.getChild(0)), node.getPayload(), visit(node.getChild(1)));
        if (node.getParent() instanceof ExpressionNode) {
            boolean needsParens = ((EExpressionOperator) node.getParent().getPayload()).isHigherPriority(
                    (EExpressionOperator) node.getPayload()
            );
            if (needsParens)
                return String.format("(%s)", output);
        }
        return output;
    }

    @Override
    public String visitBoolean(BooleanNode node) {
        return node.getPayload().toString();
    }

    @Override
    public String visitInteger(IntegerNode node) {
        return node.toString();
    }

    @Override
    public String visitFloat(FloatNode node) {
        return node.toString();
    }

    @Override
    public String visitInvert(InvertNode node) {
        return String.format("!(%s)", visit((ASTNode) node.getChild(0)));
    }

    @Override
    public String visitStart(StartNode node) {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            output += visit(node.getChild(i));
            if (i != node.getChildCount() - 1) output += "\n";
        }
        return output;
    }
}
