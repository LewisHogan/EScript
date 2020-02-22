package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.values.VariableNode;

/**
 * Represents a variable being assigned a value.
 */
public class AssignmentNode extends ASTNode<String> {
    /**
     * Creates an Assignment node to represent the Assignment operation taking place.
     *
     * @param variable A variable node describing the variable being assigned to.
     * @param value    An AST node describing a value being assigned.
     */
    public AssignmentNode(VariableNode variable, ASTNode value) {
        addChild(variable);
        addChild(value);
        setPayload("=");
    }
}
