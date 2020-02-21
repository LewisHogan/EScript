package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;

public class PrintNode extends ASTNode<String> {
    public PrintNode(ASTNode expression) {
        setPayload("print");
        addChild(expression);
    }
}
