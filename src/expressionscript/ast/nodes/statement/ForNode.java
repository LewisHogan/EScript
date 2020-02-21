package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.ConditionNode;

import java.util.List;

public class ForNode extends ASTNode<ForHead> {

    /**
     * Node which represents a for loop.
     * @param init The initial expression (e.g. i = 0)
     * @param condition The condition that must be true for the loop to be evaluated (e.g. i < 5)
     * @param next The expression that alters the initial expression's results (e.g. i = i + 1)
     * @param body Statements executed each loop
     */
    public ForNode(AssignmentNode init, ConditionNode condition, ASTNode next, List<ASTNode> body) {
        setPayload(new ForHead(init, condition, next));
        if (body != null)
            body.forEach(this::addChild);
    }
}
