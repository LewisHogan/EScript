package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;

import java.util.List;

public class BranchNode extends ASTNode<String> {
    /**
     * Node to represent conditional flow (if, else if, else).
     *
     * @param ifNode      A node representing the if part.
     * @param elseIfNodes A list of IfNodes which represents the else if parts.
     * @param elseNodes   A list of nodes that represents the actions to be taken if none of the preceding nodes
     *                    are taken.
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
