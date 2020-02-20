package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;

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
        statements.forEach(this::addChild);
    }
}
