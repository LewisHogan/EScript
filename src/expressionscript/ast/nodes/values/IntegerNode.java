package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;

public class IntegerNode extends ASTNode<Integer> {
    /**
     * Node represents an Integer value.
     *
     * @param value The integer value.
     */
    public IntegerNode(Integer value) {
        setPayload(value);
    }
}
