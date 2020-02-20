package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;

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

    @Override
    public String toString() {
        return String.format("%s %s %s", getChild(0), getPayload(), getChild(1));
    }
}
