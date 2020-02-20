package expressionscript.ast;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.StartNode;
import expressionscript.ast.nodes.statement.ExpressionNode;
import expressionscript.ast.nodes.values.*;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.IfNode;
import expressionscript.ast.nodes.statement.AssignmentNode;
import org.antlr.v4.runtime.tree.Tree;

public abstract class ASTVisitor<E> {

    public E visit(Tree node) {
        if (node instanceof ASTNode) return visit((ASTNode) node);
        return null;
    }

    public E visit(ASTNode node) {
        if (node instanceof AssignmentNode) return visitAssignment((AssignmentNode) node);
        if (node instanceof BranchNode) return visitBranch((BranchNode) node);
        if (node instanceof ConditionNode) return visitCondition((ConditionNode) node);
        if (node instanceof IfNode) return visitIf((IfNode) node);
        if (node instanceof StringNode) return visitString((StringNode) node);
        if (node instanceof VariableNode) return visitVariable((VariableNode) node);
        if (node instanceof ExpressionNode) return visitExpression((ExpressionNode) node);
        if (node instanceof BooleanNode) return visitBoolean((BooleanNode) node);
        if (node instanceof IntegerNode) return visitInteger((IntegerNode) node);
        if (node instanceof FloatNode) return visitFloat((FloatNode) node);
        if (node instanceof InvertNode) return visitInvert((InvertNode) node);
        if (node instanceof StartNode) return visitStart((StartNode) node);
        return null;
    }

    public abstract E visitAssignment(AssignmentNode node);
    public abstract E visitBranch(BranchNode node);
    public abstract E visitCondition(ConditionNode node);
    public abstract E visitIf(IfNode node);
    public abstract E visitString(StringNode node);
    public abstract E visitVariable(VariableNode node);
    public abstract E visitExpression(ExpressionNode node);
    public abstract E visitBoolean(BooleanNode node);
    public abstract E visitInteger(IntegerNode node);
    public abstract E visitFloat(FloatNode node);
    public abstract E visitInvert(InvertNode node);
    public abstract E visitStart(StartNode node);
}
