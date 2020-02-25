package escript.ast.nodes.condition;

import escript.ast.nodes.ASTNode;

public class TernaryNode extends ASTNode<ASTNode> {

    public TernaryNode(ASTNode condition, ASTNode ifTrue, ASTNode ifFalse) {
        setPayload(condition);
        addChild(ifTrue);
        addChild(ifFalse);
    }
}
