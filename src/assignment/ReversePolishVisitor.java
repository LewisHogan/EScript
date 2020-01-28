package assignment;

import java.util.ArrayDeque;
import java.util.Stack;

public class ReversePolishVisitor extends assignmentBaseVisitor<Stack<String>> {

    private static void addElements(Stack<String> stack, Iterable<String> elements) {
        for (String s : elements) stack.push(s);
    }

    @Override
    public Stack<String> visitExpression(assignmentParser.ExpressionContext ctx) {
        Stack<String> stack = new Stack<>();

        if (ctx.op != null) {
            // We have one of the operations, such as POW, MUL, ADD, or SET
            // This also means we have an expression in the right variable, and also the left IF we don't have SET
            // If SET is null, then we have one of the math operations
            if (ctx.SET() != null) stack.push(ctx.VARIABLE().getText());
            else addElements(stack, visitExpression(ctx.left));

            addElements(stack, visitExpression(ctx.right));
            stack.push(ctx.op.getText());

            return stack;
        } else {
            // Since we don't have an operation, we must have either a Number, a Variable, or a Sub Expression

            if (ctx.NUMBER() != null) {
                stack.push(ctx.NUMBER().getText());
            } else if (ctx.VARIABLE() != null) {
                stack.push(ctx.VARIABLE().getText());
            } else if (ctx.expression().size() != 0) {
                addElements(stack, visitExpression(ctx.expression(0)));
            }

            return stack;
        }
    }

    @Override
    public Stack<String> visitStatement(assignmentParser.StatementContext ctx) {
        if (ctx.expression() != null) {
            return visitExpression(ctx.expression());
        }

        return super.visitStatement(ctx);
    }
}
