package assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PythonCompilerVisitor extends assignmentBaseVisitor<String> {

    // Essentially pretty print, but slightly different for Python syntax

    private int currentIndentationLevel = 0;

    private String repeat(String token, int n) {
        String output = "";
        for (int i = 0; i < n; i++) output += token;
        return output;
    }

    @Override
    public String visitStart(assignmentParser.StartContext ctx) {
        List<String> output = new ArrayList<>();
        for (assignmentParser.StatementContext statement : ctx.statement())
            output.add(visit(statement));

        return output.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public String visitStatementBlock(assignmentParser.StatementBlockContext ctx) {
        String statements = ctx.statement().stream().map(this::visit).collect(Collectors.joining("\n"));
        return statements;
    }

    @Override
    public String visitStatementAssignment(assignmentParser.StatementAssignmentContext ctx) {
        return String.format("%s%s = %s",
                repeat("\t", currentIndentationLevel),
                ctx.ID(),
                visit(ctx.expression() != null ? ctx.expression() : ctx.condition())
        );
    }

    @Override
    public String visitStatementCondition(assignmentParser.StatementConditionContext ctx) {
        String ifPart = String.format("%sif %s:\n", repeat("\t", currentIndentationLevel++), visit(ctx.condition(0)));
        ifPart += String.format("%s", visit(ctx.statement(0)));
        currentIndentationLevel--;

        String elIfPart = "";
        for (int i = 0; i < ctx.ELSEIF().size(); i++) {
            elIfPart += String.format("%selif %s:\n", repeat("\t", currentIndentationLevel++), visit(ctx.condition(1+i)));
            elIfPart += visit(ctx.statement(1 + i)) + (i != ctx.ELSEIF().size() -1 ? "\n" : "");
            currentIndentationLevel--;
        }

        if (!elIfPart.equals("")) ifPart += "\n";

        String elsePart = "";
        if (ctx.ELSE() != null) {
            elIfPart += "\n";
            elsePart = String.format("%selse:\n%s", repeat("\t", currentIndentationLevel++), visit(ctx.statement(ctx.statement().size()-1)));
            currentIndentationLevel--;
        }

        return ifPart + elIfPart + elsePart;
    }

    @Override
    public String visitStatementConditionWithoutBranch(assignmentParser.StatementConditionWithoutBranchContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public String visitStatementReturn(assignmentParser.StatementReturnContext ctx) {
        return String.format("%sreturn %s", repeat("\t", currentIndentationLevel), visit(ctx.expression()));
    }

    @Override
    public String visitConditionOP(assignmentParser.ConditionOPContext ctx) {
        String op = ctx.op.getText();

        switch (op) {
            case "&&":
                op = "and";
                break;
            case "||":
                op = "or";
                break;
        }

        return String.format("%s %s %s", visit(ctx.left), op, visit(ctx.right));
    }

    @Override
    public String visitConditionBrackets(assignmentParser.ConditionBracketsContext ctx) {
        return String.format("(%s)", visit(ctx.condition()));
    }

    @Override
    public String visitConditionValue(assignmentParser.ConditionValueContext ctx) {
        // In Python, True and False are capitalized rather than just lower case
        return Character.toUpperCase(ctx.value.getText().charAt(0)) + ctx.value.getText().substring(1);
    }

    @Override
    public String visitConditionNot(assignmentParser.ConditionNotContext ctx) {
        return String.format("!%s", visit(ctx.condition()));
    }

    @Override
    public String visitConditionExpression(assignmentParser.ConditionExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public String visitConditionExpressionOP(assignmentParser.ConditionExpressionOPContext ctx) {
        return String.format("%s %s %s", visit(ctx.left), ctx.op.getText(), visit(ctx.right));
    }

    @Override
    public String visitExpressionValue(assignmentParser.ExpressionValueContext ctx) {
        return (ctx.SUB() != null) ? ctx.SUB().getText() : "" + ctx.val.getText();
    }

    @Override
    public String visitExpressionOP(assignmentParser.ExpressionOPContext ctx) {
        return String.format("%s %s %s", visit(ctx.left), ctx.op.getText(), visit(ctx.right));
    }

    @Override
    public String visitExpressionSQRT(assignmentParser.ExpressionSQRTContext ctx) {
        return "ERROR: SQRT NOT SUPPORTED AS OPERATOR IN PYTHON";
    }

    @Override
    public String visitExpressionAssignment(assignmentParser.ExpressionAssignmentContext ctx) {
        return String.format("%s = %s", ctx.ID(), visit(ctx.expression()));
    }

    @Override
    public String visitExpressionBrackets(assignmentParser.ExpressionBracketsContext ctx) {
        return String.format("(%s)", visit(ctx.expression()));
    }
}
