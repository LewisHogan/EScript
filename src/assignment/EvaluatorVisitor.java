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

    @Override
    public Object visitConditionOP(assignmentParser.ConditionOPContext ctx) {
        if (ctx.op.getText() == ctx.GT().getText()) {
//            return visit(ctx.left) > visit(ctx.right);
        }
        return null;
    }

    @Override
    public Object visitConditionBrackets(assignmentParser.ConditionBracketsContext ctx) {
        return null;
    }

    @Override
    public Object visitConditionExpression(assignmentParser.ConditionExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitConditionNot(assignmentParser.ConditionNotContext ctx) {
        Object condition = visit(ctx.condition());

        if (condition instanceof Boolean)
            return !((Boolean) condition);

        return condition == null;
    }

    @Override
    public Object visitStatementAssignment(assignmentParser.StatementAssignmentContext ctx) {
        return null;
    }

    @Override
    public Object visitStatementBlock(assignmentParser.StatementBlockContext ctx) {
        return null;
    }

    @Override
    public Object visitStatementCondition(assignmentParser.StatementConditionContext ctx) {
        return null;

    }

    @Override
    public Object visitStatementReturn(assignmentParser.StatementReturnContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionBrackets(assignmentParser.ExpressionBracketsContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionAssignment(assignmentParser.ExpressionAssignmentContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionOP(assignmentParser.ExpressionOPContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionSQRT(assignmentParser.ExpressionSQRTContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionValue(assignmentParser.ExpressionValueContext ctx) {
        return null;
    }

    @Override
    public Object visitStatementConditionWithoutBranch(assignmentParser.StatementConditionWithoutBranchContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitStart(assignmentParser.StartContext ctx) {
        Object result = null;
        for (int i = 0; i < ctx.statement().size(); i++) {
            result = visit(ctx.statement(i));
        }

        return result;
    }
}
