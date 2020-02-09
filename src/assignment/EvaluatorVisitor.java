package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EvaluatorVisitor extends assignmentBaseVisitor {

    // Scope Tree TODO: ASS 2
    // Each Tree contains a node which has a table
    // The table is a list of variables that exist within that scope
    // When checking if a variable exists, we start at the current scope and go up
    // returning the value of the variable in the scope
    // When setting a variable, we start at the current scope and if it exists, overwrite, heading all the way up

    // Global store for all variable assignments
    private HashMap<String, Object> symbolTable = new HashMap<>();

    @Override
    public Object visitStart(assignmentParser.StartContext ctx) {
        Object result = null;
        for (int i = 0; i < ctx.statement().size(); i++) {
            result = visit(ctx.statement(i));
            System.out.println(result);
        }

        System.out.println("END");
        return result;
    }

    @Override
    public Object visitStatementBlock(assignmentParser.StatementBlockContext ctx) {
        for (int i = 0; i < ctx.statement().size(); i++) {
            System.out.println(visit(ctx.statement(i)));
        }
        return super.visitStatementBlock(ctx);
    }

    @Override
    public Object visitStatementAssignment(assignmentParser.StatementAssignmentContext ctx) {
        Object result = null;
        if (ctx.expression() != null) {
            result = visit(ctx.expression());
        } else {
            result = visit(ctx.condition());
        }
        return result;
    }

    @Override
    public Object visitStatementCondition(assignmentParser.StatementConditionContext ctx) {
        return super.visitStatementCondition(ctx);
    }

    @Override
    public Object visitStatementConditionWithoutBranch(assignmentParser.StatementConditionWithoutBranchContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitStatementReturn(assignmentParser.StatementReturnContext ctx) {
        return super.visitStatementReturn(ctx);
    }

    @Override
    public Object visitConditionExpressionOP(assignmentParser.ConditionExpressionOPContext ctx) {
        Object left = visit(ctx.left);
        Object right = visit(ctx.right);

        if (ctx.op.getType() == assignmentParser.GT) {
            System.out.println("\tLEFT: " + left.getClass() + " " + left);
            System.out.println("\t" + ctx.op.getText());
            System.out.println("\tRIGHT: " + right.getClass() + " " + right);

            if (left instanceof Integer && right instanceof Integer) return (Integer) left > (Integer) right;
            if (left instanceof Float && right instanceof Float) return (Float) left > (Float) right;

        } else if (ctx.op.getType() == assignmentParser.LT) {
            if (left instanceof Integer && right instanceof Integer) return (Integer) left < (Integer) right;
        }

        return super.visitConditionExpressionOP(ctx);
    }

    @Override
    public Object visitConditionBrackets(assignmentParser.ConditionBracketsContext ctx) {
        return super.visitConditionBrackets(ctx);
    }

    @Override
    public Object visitConditionValue(assignmentParser.ConditionValueContext ctx) {
        // TRUE will be null if this is false, and vice versa with FALSE
        return ctx.TRUE() != null;
    }

    @Override
    public Object visitConditionNot(assignmentParser.ConditionNotContext ctx) {
        Object result = visit(ctx.condition());

        if (result instanceof Boolean) return !((Boolean) result);
        // If we have a type that can be considered truthy, return true
        if (result instanceof Integer && (Integer) result != 0) return true;
        if (result instanceof Float && (Float) result != 0) return true;
        // Any value that is not truthy is considered false
        return false;
    }

    @Override
    public Object visitConditionExpression(assignmentParser.ConditionExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitExpressionValue(assignmentParser.ExpressionValueContext ctx) {
        if (ctx.val.getType() == assignmentParser.ID) {
            System.out.print("LOOKING FOR ID " + ctx.val.getText() + ": ");
            if (symbolTable.containsKey(ctx.val.getText())) {
                System.out.println("We contain a, which is: " + symbolTable.get(ctx.val.getText()));
                return symbolTable.get(ctx.val.getText());
            } else {
                // TODO: Throw syntax exception due to no variable existing?
                return 0;
            }
        } else
        {
            String val = ctx.val.getText();

            if (isOnlyDigits(val)) {
                if (val.contains(".")) return Float.valueOf(val);
                return Integer.valueOf(val);
            }

            return val;
        }
    }

    private boolean isOnlyDigits(String token) {
        for (String s : token.split("")) {
            if (!s.matches("[1-9]")) return false;
        }
        return true;
    }

    @Override
    public Object visitExpressionOP(assignmentParser.ExpressionOPContext ctx) {
        return super.visitExpressionOP(ctx);
    }

    @Override
    public Object visitExpressionSQRT(assignmentParser.ExpressionSQRTContext ctx) {
        return super.visitExpressionSQRT(ctx);
    }

    @Override
    public Object visitExpressionAssignment(assignmentParser.ExpressionAssignmentContext ctx) {
        return super.visitExpressionAssignment(ctx);
    }

    @Override
    public Object visitExpressionBrackets(assignmentParser.ExpressionBracketsContext ctx) {
        return super.visitExpressionBrackets(ctx);
    }
}
