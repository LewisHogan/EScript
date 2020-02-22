package escript.ast.exceptions;

public class UndefinedVariableException extends Exception {
    public UndefinedVariableException(String variableName) {
        super(String.format("Use of undefined Variable: %s!", variableName));
    }
}
