package escript.ast.exceptions;

/**
 * Exception that is created when an invalid identifier is used.
 */
public class InvalidIDException extends Exception {
    /**
     * Creates an exception describing the declaration of an invalid identifier.
     *
     * @param invalidIDName The name of the identifier that is invalid.
     */
    public InvalidIDException(String invalidIDName) {
        super(String.format("Invalid use of identifier: %s", invalidIDName));
    }
}
