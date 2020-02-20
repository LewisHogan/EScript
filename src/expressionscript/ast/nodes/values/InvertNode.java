package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;

public class InvertNode extends ASTNode<String> {
    /**
     * Represents a node that should invert the value of its payload.
     * (e.g. a true value becomes false or a 3 becomes -3
     * @param value The value to be negated.
     */
    public InvertNode(ASTNode value) {
        addChild(value);
        if (value instanceof BooleanNode)
            setPayload("!");
        else
            setPayload("-");
    }
}
