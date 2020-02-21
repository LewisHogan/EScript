package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.ConditionNode;

public class InvertNode extends ASTNode<String> {
    /**
     * Represents a node that should invert the value of its payload.
     * (e.g. a true value becomes false or a 3 becomes -3
     * @param value The value to be negated.
     */
    public InvertNode(ASTNode value) {
        addChild(value);
        if (value instanceof BooleanNode || (value instanceof ConditionNode))
            setPayload("!");
        else
            setPayload("-");
    }

    /**
     * Represents a node that should invert the value of its payload.
     * (e.g. a true value becomes false or a 3 becomes -3
     * @param value The value to be negated.
     */
    public InvertNode(ASTNode value, boolean isBool) {
        addChild(value);
        if (value instanceof BooleanNode || (value instanceof ConditionNode) || isBool)
            setPayload("!");
        else
            setPayload("-");
    }
}
