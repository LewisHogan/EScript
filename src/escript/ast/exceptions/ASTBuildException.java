package escript.ast.exceptions;

/**
 * Exception that is called when a valid AST tree is unable to be built correctly.
 */
public class ASTBuildException extends Exception {
    public ASTBuildException(String message) {
        super("AST Build Exception: " + message);
    }
}
