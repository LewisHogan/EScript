package ast.nodes.values;

import ast.nodes.ASTNode;

/**
 * Represents an Integer primitive.
 */
public class IntegerNode extends ASTNode<Integer> {
    /**
     * Creates a Integer node to represent the Integer primitive being described.
     *
     * @param value The integer being represented.
     */
    public IntegerNode(Integer value) {
        setPayload(value);
    }
}
