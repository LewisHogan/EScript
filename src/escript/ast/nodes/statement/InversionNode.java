package escript.ast.nodes.statement;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.values.BooleanNode;

/**
 * Represents an inversion.
 */
public class InversionNode extends ASTNode {
    /**
     * Creates a Inversion node to represent the opposite of the value being described.
     * e.g. a true value would become false, and a positive number would become negative.
     *
     * @param value
     */
    public InversionNode(ASTNode value) {
        this(value, value instanceof BooleanNode || value instanceof ConditionNode);

    }

    /**
     * Creates a Inversion node to represent the opposite of the value being described.
     * e.g. a true value would become false
     *
     * @param value     The node to invert
     * @param isBoolean If the node
     */
    public InversionNode(ASTNode value, boolean isBoolean) {
        addChild(value);
        if (isBoolean)
            setPayload("!");
        else
            setPayload("-");
    }
}
