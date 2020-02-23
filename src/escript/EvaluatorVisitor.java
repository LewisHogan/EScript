package escript;

import escript.ast.ASTVisitor;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import escript.ast.nodes.StartNode;
import escript.ast.nodes.condition.BranchNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.condition.IfNode;
import escript.ast.nodes.statement.*;
import escript.ast.nodes.statement.payload.ForPayload;
import escript.ast.nodes.statement.payload.WhilePayload;
import escript.ast.nodes.values.*;

import java.util.HashMap;

public class EvaluatorVisitor extends ASTVisitor {

    private EvaluationOutput output;

    private HashMap<String, Object> variableMap;

    @Override
    protected Object visitStart(StartNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        variableMap = new HashMap<>();
        output = new EvaluationOutput();
        for (int i = 0; i < node.getChildCount(); i++)
            visit((ASTNode) node.getChild(i));

        output.setVariableMap(variableMap);
        return output;
    }

    @Override
    protected Object visitBranch(BranchNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        Object result = null;
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            if (child instanceof IfNode) { // If or Else If statement
                if ((Boolean) visit((ASTNode) child.getPayload())) {// if the condition is true, we execute this path
                    return visit(child);
                }
            } else {
                // Since we got this far and no if statements were true, execute all the remaining nodes as else statements
                result = visit(child);
            }
        }

        return result;
    }

    @Override
    protected Object visitBlock(BlockNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        Object result = null;
        for (int i = 0; i < node.getChildCount(); i++) {
            result = visit((ASTNode) node.getChild(i));
        }
        return result;
    }

    @Override
    protected Object visitCondition(ConditionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        // Experimental single variable condition
        if (node.getChildCount() == 1) {
            Object child = visit((ASTNode) node.getChild(0));
            if (child instanceof Boolean) return (Boolean) child;
            if (child instanceof Integer) return (Integer) child != 0;
            if (child instanceof Float) return (Float) child != 0;
        }

        Object left = node.getChild(0);
        Object right = node.getChild(1);

        EComparisonOperator op = (EComparisonOperator) node.getPayload();

        left = visit((ASTNode) left);
        right = visit((ASTNode) right);

        // When we get to the actual operation, we want both types to be the same for easy handling
        if (left.getClass() != right.getClass()) {
            if (left instanceof String)
                right = right.toString();
            else if (right instanceof String)
                left = left.toString();
            else if (left instanceof Float && right instanceof Integer)
                right = Float.valueOf((Integer) right);
            else if (left instanceof Integer && right instanceof Float)
                left = Float.valueOf((Integer) left);
        }

        // TODO: Throw exceptions if invalid
        switch (op) {
            case EQUALS:
                // String equality is weird, so we cannot just do ==
                if (left instanceof String)
                    return left.toString().equals(right);
                return left == right;
            case NOT_EQUALS:
                if (left instanceof String)
                    return !(left.toString().equals(right));
                return left != right;
            case AND:
                // Treat non 0 values as true
                if (left instanceof Boolean) return (((Boolean) left) && ((Boolean) right));
                if (left instanceof Integer) return (((Integer) left) != 0) && (((Integer) right) != 0);
                break;
            case OR:
                // We treat non 0 values as true
                if (left instanceof Boolean) return (((Boolean) left) || ((Boolean) right));
                if (left instanceof Integer && (((Integer) left) != 0)) return true;
                if (right instanceof Integer && (((Integer) right) != 0)) return true;
                break;
            case GREATER_THAN_OR_EQUALS:
                if (left instanceof Integer)
                    return (Integer) left >= (Integer) right;
                if (left instanceof Float)
                    return (Float) left >= (Float) right;
                break;
            case GREATER_THAN:
                if (left instanceof Integer)
                    return (Integer) left > (Integer) right;
                if (left instanceof Float)
                    return (Float) left > (Float) right;
                break;
            case LESS_THAN:
                if (left instanceof Integer)
                    return (Integer) left < (Integer) right;
                if (left instanceof Float)
                    return (Float) left < (Float) right;
                break;
            case LESS_THAN_OR_EQUALS:
                if (left instanceof Integer)
                    return (Integer) left <= (Integer) right;
                if (left instanceof Float)
                    return (Float) left <= (Float) right;
                break;
        }

        return false;
    }

    @Override
    protected Object visitIf(IfNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if ((Boolean) visit((ASTNode) node.getPayload())) {
            // If the condition is true, then execute all the statements
            Object result = null;
            for (int i = 0; i < node.getChildCount(); i++)
                result = visit((ASTNode) node.getChild(i));
            return result;
        }

        return false;
    }

    @Override
    protected Object visitAssignment(AssignmentNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String variableName = (String) node.getChild(0).getPayload();

        // If the variable name is one of the reserved keywords, throw an error
        switch (variableName) {
            case "print":
            case "for":
            case "while":
            case "and":
            case "or":
            case "true":
            case "false":
            case "if":
            case "else":
                throw new InvalidIDException(variableName);
        }

        Object value = visit((ASTNode) node.getChild(1));
        variableMap.put(variableName, value);
        return value;
    }

    @Override
    protected Object visitExpression(ExpressionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        EExpressionOperator op = (EExpressionOperator) node.getPayload();
        Object left = node.getChild(0);
        Object right = node.getChild(1);

        left = visit((ASTNode) left);
        right = visit((ASTNode) right);

        if (!op.isValid(left, right))
            throw new InvalidOperationException(
                    String.format("Cannot %s %s and %s!", op.name(), left, right)
            );

        // Convert to the same class inc ase is different before performing operations
        if (left.getClass() != right.getClass()) {
            if (left instanceof String) {
                right = right.toString();
            } else if (right instanceof String) {
                left = left.toString();
            } else if (left instanceof Float && right instanceof Integer)
                right = Float.valueOf((Integer) right);
            else if (left instanceof Integer && right instanceof Float)
                left = Float.valueOf((Integer) left);
        }

        // By this point we know both operands are the same type.
        switch (op) {
            case ADD:
                if (left instanceof String)
                    return String.format("%s", (String) left + right);
                if (left instanceof Integer)
                    return (Integer) left + (Integer) right;
                if (left instanceof Float)
                    return (Float) left + (Float) right;
                break;
            case SUBTRACT:
                if (left instanceof Integer)
                    return (Integer) left - (Integer) right;
                if (left instanceof Float)
                    return (Float) left - (Float) right;
                break;
            case MULTIPLY:
                if (left instanceof Integer)
                    return (Integer) left * (Integer) right;
                if (left instanceof Float)
                    return (Float) left * (Float) right;
                break;
            case DIVIDE:
                if ((Integer) right == 0) throw new InvalidOperationException("Cannot be divide by 0");
                if (left instanceof Integer)
                    return (Integer) left / (Integer) right;
                if (left instanceof Float)
                    return (Float) left / (Float) right;
                break;
            case MODULO:
                if (left instanceof Integer)
                    return (Integer) left % (Integer) right;
                if (left instanceof Float)
                    return (Float) left % (Float) right;
                break;
            case POWER:
                return Math.pow((Float) left, (Float) right);
        }

        return false;
    }

    @Override
    protected Object visitFor(ForNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        ForPayload payload = (ForPayload) node.getPayload();
        visit(payload.getInitial());
        Object result = null;
        while ((Boolean) visit(payload.getCondition())) {
            for (int i = 0; i < node.getChildCount(); i++) {
                result = visit((ASTNode) node.getChild(i));
            }
            result = visit(payload.getNext());
        }

        return result;
    }

    @Override
    protected Object visitInversion(InversionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (node.getPayload() == "!") {
            return !((Boolean) visit((ASTNode) node.getChild(0)));
        }

        // If we are here, that means that the node is a negation of a number, the only decision to make is Integer or Float
        Object child = visit((ASTNode) node.getChild(0));
        if (child instanceof Integer) return -(Integer) child;
        else if (child instanceof Float) return -(Float) child;

        throw new InvalidOperationException(child.toString() + " is not an Integer, Float or Boolean and cannot be negated!");
    }

    @Override
    protected Object visitPrint(PrintNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        output.log(visit((ASTNode) node.getChild(0)).toString());
        return null;
    }

    @Override
    protected Object visitWhile(WhileNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        WhilePayload payload = (WhilePayload) node.getPayload();
        Object result = null;
        while ((Boolean) visit(payload.getCondition())) {
            for (int i = 0; i < node.getChildCount(); i++)
                result = visit((ASTNode) node.getChild(i));
        }

        return result;
    }

    @Override
    protected Object visitBoolean(BooleanNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload();
    }

    @Override
    protected Object visitFloat(FloatNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload();
    }

    @Override
    protected Object visitInteger(IntegerNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload();
    }

    @Override
    protected Object visitString(StringNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        // Remove the extra quotes
        return ((String) node.getPayload()).substring(1, ((String) node.getPayload()).length() - 1);
    }

    @Override
    protected Object visitVariable(VariableNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (!variableMap.containsKey(node.getPayload()))
            throw new UndefinedVariableException((String) node.getPayload());
        return variableMap.get(node.getPayload());
    }
}