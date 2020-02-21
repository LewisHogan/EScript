package expressionscript.ast.nodes.statement;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.values.IntegerNode;
import expressionscript.ast.nodes.values.StringNode;

public enum EExpressionOperator {
    ADD("+", 1),
    SUBTRACT("-", 1),
    DIVIDE("/", 2),
    MULTIPLY("*", 2),
    POWER("**", 3),
    SQRT("//", 3),
    MODULO("%", 3);

    private String op;
    private int priority;

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


    public boolean areValidOperands(Object left, Object right) {
        switch (this) {
            // Special cases go here
            case ADD:
                if (left instanceof String || right instanceof String) return true;
        }

        if (left instanceof Number || right instanceof Number) return true;

        return false;
    }
}
