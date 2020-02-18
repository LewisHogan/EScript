package expressionscript.ast.nodes.expression;

import expressionscript.ast.nodes.ASTNode;

public class NumberNode extends ASTNode<Number> {
    private boolean isInteger;

    public boolean isInteger() {
        return isInteger;
    }

    public NumberNode(Integer number) {
        setPayload(number);
        isInteger = true;
    }

    public NumberNode(Float number) {
        setPayload(number);
        isInteger = false;
    }

    // add
    // if (!one.isInteger() || !two.isInteger()) // treat as floats for both and return a new NumberNode with a float
    // or something
    // otherwise treat them both as integers
    // (if one.isInteger() && two.isInteger()) //treat both as integers, otherwise assume they are floats
    // The value stored in a variable node should also be checked when doing math operations
    // e.g. getVariable("varnamehere") instanceof Integer && numbernode.isInteger() // then do math stuff
    // for variable + variable do the same as number nodes
}
