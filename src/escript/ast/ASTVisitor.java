package escript.ast;

import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import escript.ast.nodes.StartNode;
import escript.ast.nodes.condition.BranchNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.condition.IfNode;
import escript.ast.nodes.statement.*;
import escript.ast.nodes.values.*;

/**
 * Abstract class used to iterate over the Abstract Syntax Tree nodes.
 *
 * @param <E> The type of value to be returned from each node.
 */
public abstract class ASTVisitor<E> {

    public E visit(ASTNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (node instanceof StartNode) return visitStart((StartNode) node);
        if (node instanceof BranchNode) return visitBranch((BranchNode) node);
        if (node instanceof ConditionNode) return visitCondition((ConditionNode) node);
        if (node instanceof IfNode) return visitIf((IfNode) node);
        if (node instanceof AssignmentNode) return visitAssignment((AssignmentNode) node);
        if (node instanceof ExpressionNode) return visitExpression((ExpressionNode) node);
        if (node instanceof ForNode) return visitFor((ForNode) node);
        if (node instanceof InversionNode) return visitInversion((InversionNode) node);
        if (node instanceof PrintNode) return visitPrint((PrintNode) node);
        if (node instanceof WhileNode) return visitWhile((WhileNode) node);
        if (node instanceof BooleanNode) return visitBoolean((BooleanNode) node);
        if (node instanceof FloatNode) return visitFloat((FloatNode) node);
        if (node instanceof IntegerNode) return visitInteger((IntegerNode) node);
        if (node instanceof StringNode) return visitString((StringNode) node);
        if (node instanceof VariableNode) return visitVariable((VariableNode) node);

        return null;
    }

    protected abstract E visitStart(StartNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitBranch(BranchNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitCondition(ConditionNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitIf(IfNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitAssignment(AssignmentNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitExpression(ExpressionNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitFor(ForNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitInversion(InversionNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitPrint(PrintNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitWhile(WhileNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitBoolean(BooleanNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitFloat(FloatNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitInteger(IntegerNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitString(StringNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;

    protected abstract E visitVariable(VariableNode node) throws InvalidOperationException,
            UndefinedVariableException, InvalidIDException;
}
