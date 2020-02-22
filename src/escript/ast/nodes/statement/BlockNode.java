package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;

import java.util.List;

/**
 * Represents a collection of statements.
 */
public class BlockNode extends ASTNode<String> {
    /**
     * Creates a block node that represents a collection of statements.
     *
     * @param children The statements within this block.
     */
    public BlockNode(List<ASTNode> children) {
        super(children);
        setPayload("Block");
    }
}
