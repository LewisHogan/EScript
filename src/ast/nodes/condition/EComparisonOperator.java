package ast.nodes.condition;

/**
 * Represents Comparison Operators.
 */
public enum EComparisonOperator {
    EQUALS("==", 3),
    NOT_EQUALS("!=", 3),
    GREATER_THEN(">", 2),
    GREATER_THEN_OR_EQUALS(">=", 2),
    LESS_THEN("<", 2),
    LESS_THEN_OR_EQUALS("<=", 2),
    AND("&&", 1),
    OR("||", 1),
    VALUE("", 0);

    private String symbol;
    private int priority;

    /**
     * Representation of the various comparison operations that can be used for expressions.
     *
     * @param symbol   The symbol of the comparison operator.
     * @param priority The priority of the comparison operator.
     */
    EComparisonOperator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public boolean isHigherPriority(EComparisonOperator other) {
        return priority > other.priority;
    }
}
