package expressionscript.ast;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.StartNode;
import expressionscript.ast.nodes.statement.*;
import expressionscript.ast.nodes.values.*;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.exceptions.TypeException;
import org.antlr.v4.runtime.tree.Tree;

public abstract class ASTVisitor<E> {

    public E visit(Tree node) throws TypeException {
        if (node instanceof ASTNode) return visit((ASTNode) node);
        return null;
    }

    public E visit(ASTNode node) throws TypeException {
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
        if (node instanceof WhileNode) return visitWhile((WhileNode) node);
        if (node instanceof PrintNode) return visitPrint((PrintNode) node);
        return null;
    }

    public abstract E visitAssignment(AssignmentNode node) throws TypeException;

    public abstract E visitBranch(BranchNode node) throws TypeException;

    public abstract E visitCondition(ConditionNode node) throws TypeException;

    public abstract E visitIf(IfNode node) throws TypeException;

    public abstract E visitString(StringNode node) throws TypeException;

    public abstract E visitVariable(VariableNode node) throws TypeException;

    public abstract E visitExpression(ExpressionNode node) throws TypeException;

    public abstract E visitBoolean(BooleanNode node) throws TypeException;

    public abstract E visitInteger(IntegerNode node) throws TypeException;

    public abstract E visitFloat(FloatNode node) throws TypeException;

    public abstract E visitInvert(InvertNode node) throws TypeException;

    public abstract E visitStart(StartNode node) throws TypeException;

    public abstract E visitWhile(WhileNode node) throws TypeException;

    public abstract E visitPrint(PrintNode node) throws TypeException;
}
