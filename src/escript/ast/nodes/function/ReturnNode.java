package escript.ast.nodes.function;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.function.payload.ReturnPayload;

/**
 * Represents a value being returned from a function
 */
public class ReturnNode extends ASTNode<ReturnPayload> {
    public ReturnNode(ASTNode valueToReturn) {
        setPayload(new ReturnPayload(valueToReturn));
        addChild(valueToReturn);
    }
}
