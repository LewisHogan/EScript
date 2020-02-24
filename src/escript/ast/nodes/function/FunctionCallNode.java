package escript.ast.nodes.function;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.values.VariableNode;

import java.util.List;

/**
 * Represents a function call
 */
public class FunctionCallNode extends ASTNode<String> {
    /**
     * Creates a node to represent a function call.
     *
     * @param parameters A list of values for the variables that will be used as parameters.
     */
    public FunctionCallNode(String functionName, List<ASTNode> parameters) {
        if (parameters != null) {
            parameters.stream().forEach(this::addChild);
            setPayload(functionName);
        }
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getPayload(), getChildCount());
    }
}
