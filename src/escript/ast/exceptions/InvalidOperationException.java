package escript.ast.exceptions;

import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.statement.EExpressionOperator;

/**
 * Exception created when an invalid operation is attempted to perform e.g. (string - string)
 */
public class InvalidOperationException extends Exception {
    public InvalidOperationException(EExpressionOperator operation) {
        super(String.format("Invalid Operation Exception: %s", operation));

    }

    public InvalidOperationException(EComparisonOperator operation) {
        super(String.format("Invalid Operation Exception: %s", operation));
    }

    public InvalidOperationException(String message) {
        super("Invalid Operation Exception: " + message);
    }
}
