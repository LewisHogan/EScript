package expressionscript.ast.nodes.values;

import expressionscript.ast.nodes.ASTNode;

public class VariableNode extends ASTNode<String> {
    /**
     * Node represents a variable.
     * @param variableName The name of the variable in the symbol table.
     */
    public VariableNode(String variableName) {
        setPayload(variableName);
    }
}
