package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;

public class BooleanNode extends ASTNode<Boolean> {
    public BooleanNode(boolean value) {
        setPayload(value);
    }
}
