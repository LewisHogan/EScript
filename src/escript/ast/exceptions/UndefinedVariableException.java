package escript.ast.exceptions;

/**
 * An exception representing the use of a variable that has not yet been defined.
 */
public class UndefinedVariableException extends Exception {
    /**
     * Creates an exception to describe the use of an undefined variable.
     *
     * @param variableName The name of the undefined variable.
     */
    public UndefinedVariableException(String variableName) {
        super(String.format("Use of undefined Variable: %s!", variableName));
    }
}
