package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.values.BooleanNode;

public class ConditionNode extends ASTNode<EComparisonOperator> {

    /**
     * Node represents an individual comparison (e.g. a == b or 3 > 2).
     *
     * @param left     Left operand.
     * @param operator Comparison Operator.
     * @param right    Right operand.
     */
    public ConditionNode(ASTNode left, EComparisonOperator operator, ASTNode right) {
        addChild(left);
        addChild(right);
        setPayload(operator);
    }

    // Used for things like if is_bankrupt, rather than is_bankrupt == true
    public ConditionNode(ASTNode value) {
        addChild(value);
        setPayload(EComparisonOperator.EQUALS);
    }

    @Override
    public String toString() {
        if (getChildCount() == 1) return String.format("%s", getChild(0));
        return String.format("%s %s %s", getChild(0), getPayload(), getChild(1));
    }
}
