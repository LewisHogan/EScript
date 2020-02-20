package expressionscript.ast.nodes.statement;


import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.values.VariableNode;

public class AssignmentNode extends ASTNode<String> {
    /**
     * Node represents a variable being assigned a value.
     *
     * @param left  A variable node representing the variable to have a value assigned.
     * @param right The node being assigned to the variable.
     */
    public AssignmentNode(VariableNode left, ASTNode right) {
        addChild(left);
        addChild(right);
        setPayload("=");
    }
}
