package expressionscript.ast.nodes.expression;

import expressionscript.ast.nodes.ASTNode;

public class ExpressionNode extends ASTNode<EExpressionOperator> {
    public ExpressionNode(ASTNode left, EExpressionOperator operator, ASTNode right) {
        this.addChild(left);
        this.addChild(right);
        setPayload(operator);
    }
}
