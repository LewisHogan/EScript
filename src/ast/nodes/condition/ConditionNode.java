package ast.nodes.condition;

import ast.nodes.ASTNode;

/**
 * Represents an individual condition
 */
public class ConditionNode extends ASTNode<EComparisonOperator> {
    /**
     * Creates a Condition node to represent a Condition being described.
     * <p>
     * e.g. a == b
     *
     * @param left     The left operand in the condition.
     * @param operator The comparison operator.
     * @param right    The right operand in the condition.
     */
    public ConditionNode(ASTNode left, EComparisonOperator operator, ASTNode right) {
        addChild(left);
        addChild(right);
        setPayload(operator);
    }

    @Override
    public String toString() {
        // We keep this statement for situations like if (my_boolean_here), where the comparison is unary.
        if (getChildCount() == 1) getChild(0).toString();

        return String.format("%s %s %s", getChild(0), getPayload(), getChild(1));
    }
}
