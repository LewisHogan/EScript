package escript.ast.exceptions;

/**
 * Exception that is called when a valid AST tree is unable to be built correctly.
 */
public class ASTBuildException extends Exception {
    /**
     * Creates an exception that represents an issue preventing the AST from being created.
     *
     * @param message The reason the AST cannot be built.
     */
    public ASTBuildException(String message) {
        super("AST Build Exception: " + message);
    }
}
