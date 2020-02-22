package escript.ast.nodes.values;

import escript.ast.nodes.ASTNode;

/**
 * Represents a String primitive.
 */
public class StringNode extends ASTNode<String> {

    /**
     * Creates a String node to represent the String value being described. Note: The value in the payload has quotes
     * added to both sides, these should be stripped in any visitor that requires the original string value.
     *
     * @param value The string contents.
     */
    public StringNode(String value) {
        setPayload(String.format("\"%s\"", value));
    }
}
