package expressionscript.ast.nodes.condition;

public enum EComparisonOperator {
    EQUALS("==", 3),
    NOT_EQUALS("!=", 3),
    GREATER_THEN(">", 2),
    GREATER_THEN_OR_EQUALS(">=", 2),
    LESS_THEN("<", 2),
    LESS_THEN_OR_EQUALS("<=", 2),
    AND("&&", 1),
    OR("||", 1);

    public String op;
    public int priority;

    EComparisonOperator(String s, int priority) {
        this.op = s;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return op;
    }

    public boolean isHigherPriority(EComparisonOperator other) {
        return isHigherPriority(other.priority);
    }

    public boolean isHigherPriority(int priority) {
        return this.priority > priority;
    }
}
