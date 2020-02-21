package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;

public class PrintNode extends ASTNode<String> {
    public PrintNode(ASTNode thingToPrint) {
        setPayload("print");
        addChild(thingToPrint);
    }
}
