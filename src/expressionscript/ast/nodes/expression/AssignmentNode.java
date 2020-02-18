package expressionscript.ast.nodes.expression;

import expressionscript.ast.nodes.ASTNode;

public class AssignmentNode extends ASTNode {
    public AssignmentNode(VariableNode left, ASTNode right) {
        addChild(left);
        addChild(right);
        setPayload("=");
    }
}
