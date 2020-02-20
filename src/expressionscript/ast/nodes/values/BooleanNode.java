package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;

public class BooleanNode extends ASTNode<Boolean> {
    /**
     * Node that represents true or false values.
     *
     * @param isTrue If the Bool value is True (otherwise is False).
     */
    public BooleanNode(boolean isTrue) {
        setPayload(isTrue);
    }
}
