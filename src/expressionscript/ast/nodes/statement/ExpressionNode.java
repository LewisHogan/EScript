package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;

public class ExpressionNode extends ASTNode {
    /**
     * Node that represents a single expression (e.g. 1 + a, or 2 * 3).
     *
     * @param left     Left Operand.
     * @param operator Expression Operator (+, *, /, etc)
     * @param right    Right Operand.
     */
    public ExpressionNode(ASTNode left, EExpressionOperator operator, ASTNode right) {
        // TODO: Replace taking as AST with taking something that implements IExpression
        addChild(left);
        addChild(right);
        setPayload(operator);
    }
}
