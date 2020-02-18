package expressionscript;

import expressionscript.ast.ASTVisitor;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.*;
import expressionscript.ast.nodes.expression.*;

public class PrettyPrintVisitor extends ASTVisitor<String> {

    private int indentationLevel = 0;

    private String indent(int n) {
        String indents = "";
        for (int i = 0; i < n; i++) {
            indents += "\t";
        }
        return indents;
    }

    @Override
    public String visit(ASTNode node) {
        if (node.getPayload() instanceof String) {
            if (node.getPayload().equals("Start")) {
                String output = "";
                for (int i = 0; i < node.getChildCount(); i++) {
                    output += visit((ASTNode) node.getChild(i)) + ";";
                    if (i != node.getChildCount() - 1) output += "\n";
                }
                return output;
            } else if (node.getPayload().equals("Block")) {
                String output = "";
                for (int i = 0; i < node.getChildCount(); i++) {
                    output += visit((ASTNode) node.getChild(i));
                    if (i != node.getChildCount() - 1) output += "\n";
                }
                return output;
            }
        }
        return super.visit(node);
    }

    @Override
    public String visitAssignment(AssignmentNode node) {
        return String.format("%s%s = %s", indent(indentationLevel), node.getChild(0).getPayload(), visit((ASTNode) node.getChild(1)));
    }

    @Override
    public String visitExpression(ExpressionNode node) {
        return String.format("%s %s %s", visit((ASTNode) node.getChild(0)), ((EExpressionOperator) node.getPayload()).op, visit((ASTNode) node.getChild(1)));
    }

    @Override
    public String visitString(StringNode node) {
        return node.getPayload().toString();
    }

    @Override
    public String visitVariable(VariableNode node) {
        return node.getPayload().toString();
    }

    @Override
    public String visitNumber(NumberNode node) {
        return node.getPayload().toString();
    }

    @Override
    public String visitIf(IfNode node) {
        // TODO: Find out if this node is representing an If, an ElseIf or an Else
        boolean isElseIf = node.getParent() instanceof BranchNode
                && node.getParent().getChild(0) != node;

        String formatString = indent(indentationLevel++) + (isElseIf ? "else if" : "if") + " %s %s";
        boolean hasMultipleStatements = node.getChild(0).getChildCount() > 1;

        String block;
        if (hasMultipleStatements) {
            String statements = "";
            for (int i = 0; i < node.getChildCount(); i++) {
                String statement = visit((ASTNode) node.getChild(i));
                statements += statement + ";";
                if (i != node.getChildCount()-1) statements += "\n";
            }
            block = String.format("{\n%s\n%s}", statements, indent(indentationLevel-1));
        } else {
            block = "\n" + visit((ASTNode) node.getChild(0)) + ";";
        }


        indentationLevel--;

        return String.format(formatString, node.getPayload(), block);
    }

    @Override
    public String visitCondition(ConditionNode node) {
        return String.format("%s %s %s", visit((ASTNode) node.getChild(0)), ((EComparisonOperator) node.getPayload()).op, visit((ASTNode) node.getChild(1)));
    }

    @Override
    public String visitBoolean(BooleanNode node) {
        return node.getPayload().toString();
    }

    @Override
    public String visitBranch(BranchNode node) {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            output += visit((ASTNode) node.getChild(i));
            if (i != node.getChildCount() - 1) output += "\n";
        }
        return output;
    }
}
