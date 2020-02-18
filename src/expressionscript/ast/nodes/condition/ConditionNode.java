package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;

public class ConditionNode extends ASTNode<EComparisonOperator> {
    public ConditionNode(ASTNode left, EComparisonOperator operator, ASTNode right) {
        this.addChild(left);
        this.addChild(right);
        setPayload(operator);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", getChild(0), getPayload(), getChild(1));
    }
}
