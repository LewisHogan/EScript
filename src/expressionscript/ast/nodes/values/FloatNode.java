package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;

public class FloatNode extends ASTNode<Float> {
    /**
     * Node represents an Floating point number.
     *
     * @param value The float value.
     */
    public FloatNode(Float value) {
        setPayload(value);
    }
}
