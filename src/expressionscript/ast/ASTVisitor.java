package expressionscript.ast;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.BooleanNode;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.IfNode;
import expressionscript.ast.nodes.expression.*;

public abstract class ASTVisitor<E> {
    public E visit(ASTNode node) {
        if (node instanceof AssignmentNode) return visitAssignment((AssignmentNode) node);
        if (node instanceof ExpressionNode) return visitExpression((ExpressionNode) node);
        if (node instanceof StringNode) return visitString((StringNode) node);
        if (node instanceof VariableNode) return visitVariable((VariableNode) node);
        if (node instanceof NumberNode) return visitNumber((NumberNode) node);
        if (node instanceof IfNode) return visitIf((IfNode) node);
        if (node instanceof ConditionNode) return visitCondition((ConditionNode) node);
        if (node instanceof BooleanNode) return visitBoolean((BooleanNode) node);
        if (node instanceof BranchNode) return visitBranch((BranchNode) node);

        return null;
    }

    public abstract E visitAssignment(AssignmentNode node);
    public abstract E visitExpression(ExpressionNode node);
    public abstract E visitString(StringNode node);
    public abstract E visitVariable(VariableNode node);
    public abstract E visitNumber(NumberNode node);
    public abstract E visitIf(IfNode node);
    public abstract E visitCondition(ConditionNode node);
    public abstract E visitBoolean(BooleanNode node);
    public abstract E visitBranch(BranchNode node);
}
