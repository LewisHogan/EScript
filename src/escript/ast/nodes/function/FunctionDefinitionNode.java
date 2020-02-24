package escript.ast.nodes.function;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.function.payload.FunctionDefinitionPayload;
import escript.ast.nodes.values.VariableNode;

import java.util.List;

/**
 * Represents a newly defined function.
 */
public class FunctionDefinitionNode extends ASTNode<FunctionDefinitionPayload> {
    /**
     * Creates a structure to represent a newly defined function.
     */
    public FunctionDefinitionNode(String functionName, List<VariableNode> parameters, List<ASTNode> statements) {
        setPayload(new FunctionDefinitionPayload(functionName, parameters, statements));
        if (statements != null) {
            statements.forEach(this::addChild);
        }
    }
}
