package expressionscript;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Collectors;

public class PrettyPrintVisitor extends escriptBaseVisitor<String> {

    private int currentIndentationLevel = 0;

    private String indent(int level) {
        StringBuilder indents = new StringBuilder();
        for (int i = 0; i < level; i++)
            indents.append('\t');

        return indents.toString();
    }

    @Override
    public String visitStart(escriptParser.StartContext ctx) {
        return ctx.statement().stream().map(this::visit).collect(Collectors.joining("\n"));
    }

    @Override
    public String visitStatementBlock(escriptParser.StatementBlockContext ctx) {
        currentIndentationLevel++;
        String statements = String.format("{\n%s\n}",
                ctx.statement().stream().map(this::visit).collect(Collectors.joining("\n"))
        );
        currentIndentationLevel--;
        return statements;
    }

    @Override
    public String visitStatementAssignment(escriptParser.StatementAssignmentContext ctx) {
        ParseTree result = ctx.expression() != null ? ctx.expression() : ctx.condition();
        return String.format("%s%s = %s;", indent(currentIndentationLevel), ctx.ID(), visit(result));
    }

    @Override
    public String visitStatementBranch(escriptParser.StatementBranchContext ctx) {
        String ifPart = String.format("%sif %s %s", indent(currentIndentationLevel),
                visit(ctx.condition(0)),
                visit(ctx.statement(0))
        );

        // We want to allow single line if statements, but go onto a new line for the else if part if that's the case
        StringBuilder elseIfPart;
        if (ifPart.endsWith(";")) elseIfPart = new StringBuilder("\n");
        else if (ifPart.endsWith("}")) elseIfPart = new StringBuilder(" ");
        else elseIfPart = new StringBuilder();

        for (int i = 0; i < ctx.ELSEIF().size(); i++) {
            elseIfPart.append(String.format("%selse if %s %s%s", indent(currentIndentationLevel),
                    visit(ctx.condition(i+1)),
                    visit(ctx.statement(i+1)),
                    i != (ctx.ELSEIF().size() -1) ? "\n" : ""
            ));
        }

        String elsePart = "";
        if (ctx.ELSE() != null) {
            if (elseIfPart.toString().endsWith(";")) elseIfPart.append("\n");
            else if (elseIfPart.length() != 0 && !elseIfPart.toString().endsWith("\n")) elseIfPart.append(" ");

            elsePart = String.format("%selse %s", indent(currentIndentationLevel),
                    visit(ctx.statement(ctx.statement().size()-1))
            );
        }

        return ifPart + elseIfPart + elsePart;
    }

    @Override
    public String visitStatementCondition(escriptParser.StatementConditionContext ctx) {
        return String.format("%s%s", visit(ctx.condition()), ctx.EOS() != null ? ctx.EOS().getText() : "");
    }

    @Override
    public String visitStatementReturn(escriptParser.StatementReturnContext ctx) {
        return String.format("return NOT YET IMPLEMENTED");
    }

    @Override
    public String visitConditionComparison(escriptParser.ConditionComparisonContext ctx) {
        return String.format("%s %s %s", visit(ctx.left), ctx.op.getText(), visit(ctx.right));
    }

    @Override
    public String visitConditionValue(escriptParser.ConditionValueContext ctx) {
        return ctx.value.getText();
    }

    @Override
    public String visitConditionExpression(escriptParser.ConditionExpressionContext ctx) {
        return super.visitConditionExpression(ctx);
    }

    @Override
    public String visitConditionParens(escriptParser.ConditionParensContext ctx) {
        return String.format("(%s)", visit(ctx.condition()));
    }

    @Override
    public String visitConditionInverted(escriptParser.ConditionInvertedContext ctx) {
        return String.format("!%s", visit(ctx.condition()));
    }

    @Override
    public String visitConditionLogic(escriptParser.ConditionLogicContext ctx) {
        return super.visitConditionLogic(ctx);
    }

    @Override
    public String visitExpressionValue(escriptParser.ExpressionValueContext ctx) {
        return ctx.val.getText();
    }

    @Override
    public String visitExpressionMath(escriptParser.ExpressionMathContext ctx) {
        return String.format("%s %s %s", visit(ctx.left), ctx.op.getText(), visit(ctx.right));
    }

    @Override
    public String visitExpressionParens(escriptParser.ExpressionParensContext ctx) {
        return String.format("(%s)", visit(ctx.expression()));
    }

    @Override
    public String visitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx) {
        return String.format("%s %s %s", ctx.ID(), ctx.SET().getText(), visit(ctx.expression()));
    }
}