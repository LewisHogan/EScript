package assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrettyPrintVisitor extends assignmentBaseVisitor<String> {

    private String repeat(String str, int n) {
        String output = "";
        for (int i = 0; i < n; i++) {
            output += str;
            if (i != n - 1)
                output += " ";
        }
        return str;
    }

    @Override
    public String visitConditionOP(assignmentParser.ConditionOPContext ctx) {
        return String.format("%s %s %s", visit(ctx.left), ctx.op.getText(), visit(ctx.right));
    }

    @Override
    public String visitConditionBrackets(assignmentParser.ConditionBracketsContext ctx) {
        return String.format("(%s)", visit(ctx.condition()));
    }

    @Override
    public String visitConditionExpression(assignmentParser.ConditionExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public String visitConditionNot(assignmentParser.ConditionNotContext ctx) {
        return String.format("!%s", visit(ctx.condition()));
    }

    @Override
    public String visitStatementAssignment(assignmentParser.StatementAssignmentContext ctx) {
        return String.format("%s = %s%s",
                ctx.ID(),
                visit(ctx.expression() != null ? ctx.expression() : ctx.condition()),
                ctx.STATEMENTSEP() != null ? ctx.STATEMENTSEP().getText() : ""
        );
    }

    @Override
    public String visitStatementBlock(assignmentParser.StatementBlockContext ctx) {
        String statements = ctx.statement().stream().map(this::visit).map((s -> "\t" + s)).collect(Collectors.joining("\n"));
        return String.format("{\n%s\n}", statements);
    }

    @Override
    public String visitStatementCondition(assignmentParser.StatementConditionContext ctx) {
        String ifPart = String.format("if %s %s", visit(ctx.condition(0)), visit(ctx.statement(0)));
        String elseIfPart = ifPart.endsWith(";") ? "\n" : "";
        for (int i = 0; i < ctx.ELSEIF().size(); i++) {
            elseIfPart += String.format("else if %s %s", visit(ctx.condition(1 + i)), visit(ctx.statement(1 + i)));
            if (i != ctx.ELSEIF().size() - 1) elseIfPart += "\n";
        }

        int elseIndex = ctx.ELSEIF().size() + 1;

        boolean shouldFormatElse = elseIfPart.endsWith(";");
        String elsePart = ctx.ELSE() != null ? String.format(shouldFormatElse ? "\nelse %s" : "else" +
                " %s", visit(ctx.statement(elseIndex))) : "";

        return ifPart + elseIfPart + elsePart;

    }

    @Override
    public String visitStatementReturn(assignmentParser.StatementReturnContext ctx) {
        return String.format("return %s%s",
                visit(ctx.expression()),
                ctx.STATEMENTSEP() != null ? ctx.STATEMENTSEP().getText() : ""
        );
    }

    @Override
    public String visitExpressionBrackets(assignmentParser.ExpressionBracketsContext ctx) {
        return String.format("(%s)", visit(ctx.expression()));
    }

    @Override
    public String visitExpressionAssignment(assignmentParser.ExpressionAssignmentContext ctx) {
        return String.format("%s = %s", ctx.ID(), visit(ctx.expression()));
    }

    @Override
    public String visitExpressionOP(assignmentParser.ExpressionOPContext ctx) {
        return String.format("%s %s %s", visit(ctx.left), ctx.op.getText(), visit(ctx.right));
    }

    @Override
    public String visitExpressionSQRT(assignmentParser.ExpressionSQRTContext ctx) {
        return String.format("//%s", visit(ctx.expression()));
    }

    @Override
    public String visitExpressionValue(assignmentParser.ExpressionValueContext ctx) {
        return (ctx.SUB() != null) ? ctx.SUB().getText() : "" + ctx.val.getText();
    }

    @Override
    public String visitStatementConditionWithoutBranch(assignmentParser.StatementConditionWithoutBranchContext ctx) {
        return visit(ctx.condition()) + (ctx.STATEMENTSEP() != null ? ctx.STATEMENTSEP().getText() : "");
    }

    @Override
    public String visitStart(assignmentParser.StartContext ctx) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < ctx.statement().size(); i++)
            output.add(visit(ctx.statement(i)));

        return output.stream().collect(Collectors.joining("\n"));
    }
}

