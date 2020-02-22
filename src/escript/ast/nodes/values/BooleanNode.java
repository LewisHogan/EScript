package escript.ast.nodes.values;

import escript.ast.nodes.ASTNode;

/**
 * Represents a Boolean primitive.
 */
public class BooleanNode extends ASTNode<Boolean> {
    /**
     * Creates a Boolean Node to represent the Boolean value specified.
     *
     * @param value true, if the Boolean value is true, else false.
     */
    public BooleanNode(boolean value) {
        setPayload(value);
    }
}
