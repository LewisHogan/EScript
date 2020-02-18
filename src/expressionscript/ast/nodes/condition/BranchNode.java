package expressionscript.ast.nodes.condition;

import expressionscript.ast.nodes.ASTNode;

import java.util.List;

public class BranchNode extends ASTNode<String> {

    public BranchNode(IfNode ifNode, List<IfNode> elseIfNodes, ASTNode elseNode) {
        addChild(ifNode);
        elseIfNodes.forEach(this::addChild);
        addChild(elseNode);
        setPayload("Branch");
    }

    public BranchNode(IfNode ifNode, List<IfNode> elseIfNodes) {
        addChild(ifNode);
        elseIfNodes.forEach(this::addChild);
        setPayload("Branch");
    }

    public BranchNode(IfNode ifNode) {
        addChild(ifNode);
        setPayload("Branch");
    }
}
