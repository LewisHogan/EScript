package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;

/**
 * Represents a mathematical expression
 */
public class ExpressionNode extends ASTNode {
    /**
     * Creates an expression node to represent the expression being described.
     *
     * @param left     The left operand.
     * @param operator The operation.
     * @param right    The right operand.
     */
    public ExpressionNode(ASTNode left, EExpressionOperator operator, ASTNode right) {
        addChild(left);
        addChild(right);
        setPayload(operator);
    }
}
