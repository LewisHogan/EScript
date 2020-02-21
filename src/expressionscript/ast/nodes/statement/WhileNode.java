package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.ConditionNode;

import java.util.List;

public class WhileNode extends ASTNode<WhileHead> {
    /**
     * Node which represents a while statement.
     * @param condition Condition node to evaluate.
     * @param statements List of statement nodes to evaluate while the condition is true.
     */
    public WhileNode(ConditionNode condition, List<ASTNode> statements) {
        setPayload(new WhileHead(condition));
        if (statements != null)
            statements.forEach(this::addChild);
    }
}
