package escript.ast.nodes.statement.payload;

import escript.ast.nodes.condition.ConditionNode;

/**
 * Represents the head of a while loop e.g. while (x > 3)
 */
public class WhilePayload {
    private ConditionNode condition;

    /**
     * Creates a structure to hold the head of a while loop.
     *
     * @param condition The condition that must be true for the body of the while loop to execute.
     */
    public WhilePayload(ConditionNode condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return String.format("while %s", condition);
    }

    public ConditionNode getCondition() {
        return condition;
    }
}
