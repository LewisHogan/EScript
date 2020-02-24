package escript.ast.nodes.function.payload;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.statement.AssignmentNode;
import escript.ast.nodes.values.VariableNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the head of a function payload (e.g. f(a, b, c, d) it would represent the a, b, c, d part
 */
public class FunctionDefinitionPayload {
    private String functionName;
    private List<VariableNode> parameters;
    private List<ASTNode> statements;

    public List<VariableNode> getParameters() {
        return parameters;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<ASTNode> getStatements() {
        return statements;
    }

    /**
     * Creates a structure to hold the head of a function.
     * @param functionName
     * @param parameters
     */
    public FunctionDefinitionPayload(String functionName, List<VariableNode> parameters, List<ASTNode> statements) {
        this.functionName = functionName;
        this.parameters = parameters;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return String.format("define %s(%s)", functionName, parameters.stream().map(ASTNode::toString).collect(Collectors.joining(",")));
    }
}
