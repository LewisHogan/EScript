package escript.ast.nodes.condition;

import escript.ast.nodes.ASTNode;

import java.util.List;

/**
 * Represents a variety of execution paths/conditional flow.
 */
public class BranchNode extends ASTNode<String> {
    /**
     * Creates a Branch node to represent the various pathways available.
     *
     * @param ifNode      The If pathway.
     * @param elseIfNodes The else if pathways.
     * @param elseNodes   The statements to execute in the event none of the other pathways being chosen.
     */
    public BranchNode(IfNode ifNode, List<IfNode> elseIfNodes, List<ASTNode> elseNodes) {
        addChild(ifNode);
        if (elseIfNodes != null)
            elseIfNodes.forEach(this::addChild);
        if (elseNodes != null)
            elseNodes.forEach(this::addChild);
        setPayload("Branch");
    }
}
