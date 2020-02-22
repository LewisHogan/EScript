package ast.nodes.condition;

import ast.nodes.ASTNode;

import java.util.List;

/**
 * Represents an If block.
 */
public class IfNode extends ASTNode<ConditionNode> {
    /**
     * Creates an IfNode to represent an If Condition and statements being described.
     *
     * @param condition  The condition that must be true for the statements to be executed.
     * @param statements The statements executed if the condition resolves to true.
     */
    public IfNode(ConditionNode condition, List<ASTNode> statements) {
        setPayload(condition);
        if (statements != null)
            statements.forEach(this::addChild);
    }
}
