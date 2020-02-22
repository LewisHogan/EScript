package escript.ast.nodes.statement;

/**
 * Represents Expression Operators
 */
public enum EExpressionOperator {
    ADD("+", 1),
    SUBTRACT("-", 1),
    DIVIDE("/", 2),
    MULTIPLY("*", 2),
    POWER("**", 3),
    MODULO("%", 2);

    private String symbol;
    private int priority;

    EExpressionOperator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return symbol;
    }

    /**
     * Returns if the current operation has priority.
     *
     * @param other The other expression operator to compare.
     * @return If the current operation is a higher or equal priority than the other.
     */
    public boolean isPriority(EExpressionOperator other) {
        return priority >= other.priority;
    }

    /**
     * Returns if the two operands are compatible with the current operation.
     *
     * @param left  The left operand.
     * @param right The right operand.
     * @return If the operands are both compatible with this operation.
     */
    public boolean isValid(Object left, Object right) {
        switch (this) {
            case ADD:
                // For String Concatenation, we need to support adding strings to thing
                if (left instanceof String || right instanceof String) return true;
        }

        // Numbers support all operations
        if (left instanceof Number && right instanceof Number) return true;

        return false;
    }
}
