package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.expression.ExpressionNode;

public class IfNode extends ASTNode<ConditionNode> {
    public IfNode(ConditionNode condition, ASTNode action) {
        setPayload(condition);
        addChild(action);
    }
}
