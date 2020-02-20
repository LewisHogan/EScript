package expressionscript;

import expressionscript.ast.ASTVisitor;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.StartNode;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.EComparisonOperator;
import expressionscript.ast.nodes.statement.IfNode;
import expressionscript.ast.nodes.statement.AssignmentNode;
import expressionscript.ast.nodes.statement.ExpressionNode;
import expressionscript.ast.nodes.statement.WhileNode;
import expressionscript.ast.nodes.values.*;
import expressionscript.exceptions.TypeException;

public class PythonCompileVisitor extends ASTVisitor<String> {
    @Override
    public String visitAssignment(AssignmentNode node) throws TypeException {
        return String.format("%s %s %s", visit(node.getChild(0)), node.getPayload(), visit(node.getChild(1)));
    }

    @Override
    public String visitBranch(BranchNode node) throws TypeException {
        return null;
    }

    @Override
    public String visitCondition(ConditionNode node) throws TypeException {
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
    public String visitIf(IfNode node) throws TypeException {
        return null;
    }

    @Override
    public String visitString(StringNode node) throws TypeException {
        return node.getPayload().toString();
    }

    @Override
    public String visitVariable(VariableNode node) throws TypeException {
        return node.getPayload().toString();
    }

    @Override
    public String visitExpression(ExpressionNode node) throws TypeException {
        return null;
    }

    @Override
    public String visitBoolean(BooleanNode node) throws TypeException {
        return (Boolean) node.getPayload() == true ? "True" : "False";
    }

    @Override
    public String visitInteger(IntegerNode node) throws TypeException {
        return node.getPayload().toString();
    }

    @Override
    public String visitFloat(FloatNode node) throws TypeException {
        return node.getPayload().toString();
    }

    @Override
    public String visitInvert(InvertNode node) throws TypeException {
        return String.format("not %s", visit(node.getChild(0)));
    }

    @Override
    public String visitStart(StartNode node) throws TypeException {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            output += visit(node.getChild(i));
            if (i != node.getChildCount() - 1) output += "\n";
        }
        return output;
    }

    @Override
    public String visitWhile(WhileNode node) throws TypeException {
        return null;
    }
}
