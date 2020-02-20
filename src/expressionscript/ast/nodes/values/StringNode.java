package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;

public class StringNode extends ASTNode<String> {
    /**
     * Node represents a String.
     *
     * @param value The value of the string.
     */
    public StringNode(String value) {
        setPayload(String.format("%s", value));
    }
}
