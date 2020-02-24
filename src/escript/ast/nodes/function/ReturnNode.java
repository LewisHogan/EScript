package escript.ast.nodes.function;

import escript.ast.nodes.ASTNode;

/**
 * Represents a value being returned from a function
 */
public class ReturnNode extends ASTNode<ASTNode> {
    public ReturnNode(ASTNode valueToReturn) {
        setPayload(valueToReturn);
    }

    @Override
    public String toString() {
        return "Return " + getPayload().toString();
    }
}
