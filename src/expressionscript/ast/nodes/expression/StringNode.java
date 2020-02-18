package expressionscript.ast.nodes.expression;

import expressionscript.ast.nodes.ASTNode;

public class StringNode extends ASTNode<String> {
    public StringNode(String str) {
        setPayload(str);
    }
}
