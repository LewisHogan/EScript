package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.statement.payload.WhilePayload;

import java.util.List;

/**
 * Represents a while loop.
 */
public class WhileNode extends ASTNode<WhilePayload> {

    /**
     * Node which represents a while loop.
     *
     * @param condition  The condition that must be true for the statements to be evaluated.
     * @param statements The statements that run while the condition is true.
     */
    public WhileNode(ConditionNode condition, List<ASTNode> statements) {
        setPayload(new WhilePayload(condition));
        if (statements != null)
            statements.forEach(this::addChild);
    }
}
