package escript.ast.exceptions;

public class InvalidIDException extends Exception {
    public InvalidIDException(String invalidIDName) {
        super(String.format("Invalid use of identifier: %s", invalidIDName));
    }
}
