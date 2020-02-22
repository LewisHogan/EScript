package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;

/**
 * Represents writing to the output.
 */
public class PrintNode extends ASTNode<String> {
    /**
     * Creates a Print node to represent printing data to the output.
     *
     * @param value The node containing the value of the thing to print.
     */
    public PrintNode(ASTNode value) {
        setPayload("Print");
        addChild(value);
    }
}
