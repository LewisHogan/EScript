package assignment;

import java.util.ArrayList;
import java.util.List;

public class PrettyPrintVisitor extends assignmentBaseVisitor<String> {
    @Override
    public String visitExpression(assignmentParser.ExpressionContext ctx) {
        if (ctx.op != null) {

            String left;
            String op;
            String right;

            // We have one of the operations, such as POW, MUL, ADD, or SET
            // This also means we have an expression in the right variable, and also the left IF we don't have SET
            // If SET is null, then we have one of the math operations
            if (ctx.SET() != null) left = ctx.VARIABLE().getText();
            else left = visitExpression(ctx.left);

            right = visitExpression(ctx.right);
            op = ctx.op.getText();

            return String.format("%s %s %s", left, op, right);
        } else {
            // Since we don't have an operation, we must have either a Number, a Variable, or a Sub Expression

            if (ctx.NUMBER() != null) {
                return ctx.NUMBER().getText();
            } else if (ctx.VARIABLE() != null) {
                return ctx.VARIABLE().getText();
            } else if (ctx.STRING() != null) {
                return ctx.STRING().getText();
            } else if (ctx.expression().size() != 0) {
                return '(' + visitExpression(ctx.expression(0)) + ')';
            }

            return "";
        }
    }

    @Override
    public String visitStatement(assignmentParser.StatementContext ctx) {
        if (ctx.expression() != null) {
            return visitExpression(ctx.expression());
        } else {
            return "";
        }
    }

    @Override
    public String visitStart(assignmentParser.StartContext ctx) {
        List<assignmentParser.StatementContext> statements = ctx.statement();
        if (statements == null) return super.visitStart(ctx);

        List<String> lines = new ArrayList<>();

        for (assignmentParser.StatementContext statementContext : statements)
            lines.add(visitStatement(statementContext));

        lines.removeIf((s) -> s.equals(""));

        return String.join("\n", lines);
    }
}
