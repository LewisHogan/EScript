package escript.ast.exceptions;

import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.statement.EExpressionOperator;

/**
 * Exception created when an invalid operation is attempted to perform e.g. (string - string)
 */
public class InvalidOperationException extends Exception {
    /**
     * Creates an exception to describe an invalid operation.
     *
     * @param operation The expression operation that is failing to be performed.
     */
    public InvalidOperationException(EExpressionOperator operation) {
        super(String.format("Invalid Operation Exception: %s", operation));

    }

    /**
     * Creates an exception to describe an invalid operation.
     *
     * @param operation The comparison operation that is failing to be performed.
     */
    public InvalidOperationException(EComparisonOperator operation) {
        super(String.format("Invalid Operation Exception: %s", operation));
    }

    /**
     * Creates an exception to describe an invalid operation.
     *
     * @param message A message describing the operation that has failed to be performed.
     */
    public InvalidOperationException(String message) {
        super("Invalid Operation Exception: " + message);
    }
}
