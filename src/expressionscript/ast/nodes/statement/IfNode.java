package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.ConditionNode;

import java.util.List;

public class IfNode extends ASTNode<ConditionNode> {
    /**
     * Node which represents the if part of an if statement.
     *
     * @param condition Condition node to evaluate.
     * @param statements List of Statement nodes which would be evaluated if the condition proves to be true.
     */
    public IfNode(ConditionNode condition, List<ASTNode> statements) {
        setPayload(condition);
        if (statements != null)
            statements.forEach(this::addChild);
    }
}
