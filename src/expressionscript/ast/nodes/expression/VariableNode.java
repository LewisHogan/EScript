package expressionscript.ast.nodes.expression;

import expressionscript.ast.nodes.ASTNode;

public class VariableNode extends ASTNode {
    public VariableNode(String key) {
        setPayload(String.format("[%s]", key));
    }
}
