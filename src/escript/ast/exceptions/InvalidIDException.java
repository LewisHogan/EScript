package escript.ast.exceptions;

/**
 * Exception that is created when an invalid identifier is used.
 */
public class InvalidIDException extends Exception {
    public InvalidIDException(String invalidIDName) {
        super(String.format("Invalid use of identifier: %s", invalidIDName));
    }
}
