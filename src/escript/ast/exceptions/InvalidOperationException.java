package escript.ast.exceptions;

import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.statement.EExpressionOperator;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(EExpressionOperator operation) {
        super(String.format("Invalid Operation Exception: %s", operation));

    }

    public InvalidOperationException(EComparisonOperator operation) {
        super(String.format("Invalid Operation Exception: %s", operation));
    }
}
