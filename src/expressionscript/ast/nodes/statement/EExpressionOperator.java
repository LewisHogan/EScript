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
    MODULUS("%", 3);

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

    private boolean isValidOperand(ASTNode operand) {
        switch (this) {
            case ADD:
                if (operand instanceof IntegerNode) return true;
                if (operand instanceof StringNode) return true;
            case SUBTRACT:
                if (operand instanceof IntegerNode) return true;
            default:
                if (operand instanceof IntegerNode) return true; // Integer is compatible with all operations
        }
        return false;
    }

    public boolean areValidOperands(ASTNode left, ASTNode right) {
        return isValidOperand(left) && isValidOperand(right);
    }
}
