package escript.ast.nodes.values;

import escript.ast.nodes.ASTNode;

/**
 * Represents an Float primitive.
 */
public class FloatNode extends ASTNode<Float> {
    /**
     * Creates a Float node to represent the Float primitive being described.
     *
     * @param value The float being represented.
     */
    public FloatNode(Float value) {
        setPayload(value);
    }
}
