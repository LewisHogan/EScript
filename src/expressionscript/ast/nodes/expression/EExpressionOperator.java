package expressionscript.ast.nodes.expression;

public enum EExpressionOperator {
    ADD("+", 1),
    SUBTRACT("-", 1),
    DIVIDE("/", 2),
    MULTIPLY("*", 2),
    POWER("**", 3),
    SQRT("//", 3),
    MODULUS("%", 3);

    public String op;
    public int priority;

    EExpressionOperator(String s, int priority) {
        this.op = s;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return op;
    }

    public boolean isHigherPriority(EExpressionOperator other) {
        return isHigherPriority(other.priority);
    }

    public boolean isHigherPriority(int priority) {
        return this.priority > priority;
    }


}
