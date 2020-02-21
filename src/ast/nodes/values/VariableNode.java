package ast.nodes.values;

import ast.nodes.ASTNode;

/**
 * Represents a variable.
 */
public class VariableNode extends ASTNode<String> {
    /**
     * Creates a variable node to represent the specified variable.
     *
     * @param variable The identifier of the variable being represented.
     */
    public VariableNode(String variable) {
        setPayload(variable);
    }
}
