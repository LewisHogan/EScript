package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.condition.ConditionNode;

public class WhileHead {
    public ConditionNode condition;
    public WhileHead(ConditionNode condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return String.format("while %s", condition);
    }
}
