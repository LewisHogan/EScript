package escript.ast.nodes;

import java.util.List;

/**
 * Entry point to the Abstract Syntax Tree.
 */
public class StartNode extends ASTNode<String> {
    /**
     * Creates a start node that represents the entry point of the abstract syntax tree.
     *
     * @param children A list of Abstract Syntax Tree nodes.
     */
    public StartNode(List<ASTNode> children) {
        super(children);
        setPayload("Start");
    }
}
