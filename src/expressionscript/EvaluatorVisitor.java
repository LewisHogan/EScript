package expressionscript;

import expressionscript.ast.ASTVisitor;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.StartNode;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.EComparisonOperator;
import expressionscript.ast.nodes.statement.*;
import expressionscript.ast.nodes.values.*;
import expressionscript.exceptions.TypeException;

import java.util.HashMap;

public class EvaluatorVisitor extends ASTVisitor {

    int currentStatement = 0;
    private HashMap<String, Object> symbolTable = new HashMap<>();

    @Override
    public Object visitAssignment(AssignmentNode node) throws TypeException {
        String variable = node.getChild(0).getPayload().toString();
        Object value = visit(node.getChild(1));
        symbolTable.put(variable, value);
        return value;
    }

    @Override
    public Object visitBranch(BranchNode node) throws TypeException {

        int ifNodeCount = 0;
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            if (child instanceof IfNode) {
                if ((Boolean) visit((ASTNode) child.getPayload())) {
                    // We have a true if node, we can stop searching
                    return visit(child);
                }
            } else {
                ifNodeCount = i;
                break;
            }
        }

        // Run all the else parts, since our search for a true if failed
        Object result = null;
        for (int i = ifNodeCount; i < node.getChildCount(); i++) {
            result = visit((ASTNode) node.getChild(i));
        }

        return result;
    }

    @Override
    public Object visitCondition(ConditionNode node) throws TypeException {
        if (node.getChildCount() == 1) {
            Object child = visit(node.getChild(0));
            if (child instanceof Boolean) return (Boolean) child;
            return visit(node.getChild(0));
        }

        // Experimental single variable condition
        Object left = node.getChild(0);
        Object right = node.getChild(1);


        EComparisonOperator op = (EComparisonOperator) node.getPayload();

        left = visit((ASTNode) left);
        right = visit((ASTNode) right);

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

        switch (op) {
            case EQUALS:
                // String equality is weird
                if (left instanceof String)
                    return left.toString().equals(right);
                return left == right;
            case NOT_EQUALS:
                if (left instanceof String)
                    return left.toString().equals(right);
                return left != right;
            case AND:
                if (left instanceof Boolean) return (((Boolean) left) && ((Boolean) right));
                break;
            case OR:
                if (left instanceof Boolean) return (((Boolean) left) || ((Boolean) right));
                break;
            case GREATER_THEN_OR_EQUALS:
                if (left instanceof Integer)
                    return (Integer) left >= (Integer) right;
                if (left instanceof Float)
                    return (Float) left >= (Float) right;
                break;
            case GREATER_THEN:
                if (left instanceof Integer)
                    return (Integer) left > (Integer) right;
                if (left instanceof Float)
                    return (Float) left > (Float) right;
                break;
            case LESS_THEN:
                if (left instanceof Integer)
                    return (Integer) left < (Integer) right;
                if (left instanceof Float)
                    return (Float) left < (Float) right;
                break;
            case LESS_THEN_OR_EQUALS:
                if (left instanceof Integer)
                    return (Integer) left <= (Integer) right;
                if (left instanceof Float)
                    return (Float) left <= (Float) right;
                break;
        }

        return false;
    }

    @Override
    public Object visitIf(IfNode node) throws TypeException {
        if ((Boolean) visit((ASTNode) node.getPayload())) {
            Object result = null;
            for (int i = 0; i < node.getChildCount(); i++) {
                result = visit(node.getChild(i));
            }

            return result;
        }
        return "";
    }

    @Override
    public Object visitString(StringNode node) throws TypeException {
        return node.getPayload();
    }

    @Override
    public Object visitVariable(VariableNode node) throws TypeException {
        return symbolTable.get(node.getPayload());
    }

    @Override
    public Object visitExpression(ExpressionNode node) throws TypeException {
        EExpressionOperator op = (EExpressionOperator) node.getPayload();
        Object left = node.getChild(0);
        Object right = node.getChild(1);

        left = visit((ASTNode) left);
        right = visit((ASTNode) right);

        if (!op.areValidOperands(left, right))
            throw new TypeException(
                    String.format("TypeException: Cannot %s %s and %s! (Statement %s)", op.name(), left, right, currentStatement)
            );



        // Convert to the same class is different before performing operations
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

        // By this point we know both operands are the same type.
        switch (op) {
            case ADD:
                if (left instanceof String) //TODO: Remove quote marks from before appending
                    return left.toString() + right;
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
                if (left instanceof Integer)
                    return (Integer) left / (Integer) right;
                if (left instanceof Float)
                    return (Float) left / (Float) right;
                break;
            case MODULUS:
                if (left instanceof Integer)
                    return (Integer) left % (Integer) right;
                if (left instanceof Float)
                    return (Float) left % (Float) right;
                break;
            case POWER:
                return Math.pow((Float) left, (Float) right);
            case SQRT:
                break;
        }

        return null;
    }

    @Override
    public Object visitBoolean(BooleanNode node) throws TypeException {
        return (Boolean) node.getPayload();
    }

    @Override
    public Object visitInteger(IntegerNode node) throws TypeException {
        return (Integer) node.getPayload();
    }

    @Override
    public Object visitFloat(FloatNode node) throws TypeException {
        return (Float) node.getPayload();
    }

    @Override
    public Object visitInvert(InvertNode node) throws TypeException {
        Object value = visit(node.getChild(0));

        if (value instanceof Integer) {
            return -(Integer) value;
        }

        if (value instanceof Float) {
            return -(Float) value;
        }

        if (value instanceof Boolean) {
            return !(Boolean) value;
        }

        throw new TypeException(String.format("TypeException: Cannot Invert Value of %s! (Statement %s)", value, currentStatement));
    }

    @Override
    public Object visitStart(StartNode node) throws TypeException {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            output += visit(node.getChild(i));
            if (i != node.getChildCount() - 1) output += "\n";
        }
        return output + "\n" + symbolTable.toString();
    }

    @Override
    public Object visitWhile(WhileNode node) throws TypeException {
        Object result = null;
        while ((Boolean) visit((ASTNode) node.getPayload())) {
            for (int i = 0; i < node.getChildCount(); i++) {
                result = visit(node.getChild(i));
            }
        }
        return result;
    }
}
