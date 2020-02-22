package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.statement.payload.ForPayload;

import java.util.List;

/**
 * Represents a for loop.
 */
public class ForNode extends ASTNode<ForPayload> {
    /**
     * Creates a For node to represent a for loop.
     *
     * @param initial   The initial assignment statement.
     * @param condition The condition that must be true for the loop to continue.
     * @param next      The statement that is run after the statements in the for loop body.
     * @param body      The statements that are run in every iteration of the for loop.
     */
    public ForNode(AssignmentNode initial, ConditionNode condition, ASTNode next, List<ASTNode> body) {
        setPayload(new ForPayload(initial, condition, next));
        if (body != null)
            body.forEach(this::addChild);
    }
}
