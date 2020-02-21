package ast.nodes;

import java.util.List;

/**
 * Entry point to the Abstract Syntax Tree.
 */
public class StartNode extends ASTNode<String> {
    public StartNode(List<ASTNode> children) {
        super(children);
        setPayload("Start");
    }
}
