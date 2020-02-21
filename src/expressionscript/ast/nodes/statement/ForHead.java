package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.ConditionNode;

public class ForHead {
    public AssignmentNode init;
    public ConditionNode condition;
    public ASTNode next;

    public ForHead(AssignmentNode init, ConditionNode condition, ASTNode next) {
        this.init = init;
        this.condition = condition;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("for %s; %s; %s",
                String.format("%s %s %s", init.getChild(0), init, init.getChild(1)),
                condition,
                next instanceof AssignmentNode ? String.format("%s %s %s",
                        next.getChild(0),
                        next, next.getChild(1).toString()
                ) : next
        );
    }
}
