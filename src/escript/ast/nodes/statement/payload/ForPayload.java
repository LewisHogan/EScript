package escript.ast.nodes.statement.payload;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.statement.AssignmentNode;

/**
 * Represents the head of a for loop e.g. for (i = 0; i < 10; i++)
 */
public class ForPayload {
    private AssignmentNode initial;
    private ConditionNode condition;
    private ASTNode next;

    /**
     * Creates a structure to hold the head of a for loop.
     *
     * @param initial   The initial assignment statement.
     * @param condition The condition that must be true for the loop to continue.
     * @param next      The statement that is run after the statements in the for loop body.
     */
    public ForPayload(AssignmentNode initial, ConditionNode condition, ASTNode next) {
        this.initial = initial;
        this.condition = condition;
        this.next = next;
    }

    public AssignmentNode getInitial() {
        return initial;
    }

    public ConditionNode getCondition() {
        return condition;
    }

    public ASTNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        String initialFragment = String.format("%s %s %s", initial.getChild(0), initial, initial.getChild(1));
        String nextFragment = next instanceof AssignmentNode ?
                String.format("%s %s %s", next.getChild(0), next, next.getChild(1)) : next.toString();

        return String.format("for (%s; %s; %s)", initialFragment, condition, nextFragment);
    }
}
