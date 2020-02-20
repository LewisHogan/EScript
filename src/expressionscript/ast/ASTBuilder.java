package expressionscript.ast;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.StartNode;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.EComparisonOperator;
import expressionscript.ast.nodes.condition.IfNode;
import expressionscript.ast.nodes.statement.AssignmentNode;
import expressionscript.ast.nodes.statement.EExpressionOperator;
import expressionscript.ast.nodes.statement.ExpressionNode;
import expressionscript.ast.nodes.values.*;
import expressionscript.escriptBaseVisitor;
import expressionscript.escriptParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Explores the Concrete Syntax Tree created by Antlr and produces a simplified AST.
 */
public class ASTBuilder extends escriptBaseVisitor {

    @Override
    public Object visitStart(escriptParser.StartContext ctx) {
        Object parts = ctx.statement().stream().map(this::visit).collect(Collectors.toList());
        return new StartNode(
                (List<ASTNode>) parts
        );
    }

    @Override
    public Object visitStatementBlock(escriptParser.StatementBlockContext ctx) {
        return ctx.statement().stream().map(this::visit).collect(Collectors.toList());
    }

    @Override
    public ASTNode visitStatementAssignment(escriptParser.StatementAssignmentContext ctx) {
        return new AssignmentNode(
                new VariableNode(ctx.ID().getText()),
                (ASTNode) (ctx.condition() != null ? visit(ctx.condition()) : visit(ctx.expression()))
        );
    }

    @Override
    public ASTNode visitStatementBranch(escriptParser.StatementBranchContext ctx) {
        Object ifStatement = visit(ctx.statement(0));
        if (!(ifStatement instanceof List)) ifStatement = Arrays.asList(ifStatement);

        IfNode ifNode = new IfNode(
                (ConditionNode) visit(ctx.condition(0)),
                (List) ifStatement
        );

        List<IfNode> elseIfNodes = new ArrayList();
        for (int i = 0; i < ctx.ELSEIF().size(); i++) {
            Object elseIfStatement = visit(ctx.statement(i + 1));
            if (!(elseIfStatement instanceof List)) elseIfStatement = Arrays.asList(elseIfStatement);
            Object c = visit(ctx.condition(i + 1));
            elseIfNodes.add(
                    new IfNode(
                            (ConditionNode) c,
                            (List<ASTNode>) elseIfStatement
                    )
            );
        }

        Object elseStatement = null;
        if (ctx.ELSE() != null) {
            elseStatement = visit(ctx.statement(ctx.statement().size() - 1));
            if (!(elseStatement instanceof List)) elseStatement = Arrays.asList(elseStatement);
        }

        return new BranchNode(
                ifNode,
                elseIfNodes,
                (List) elseStatement
        );
    }

    @Override
    public Object visitStatementWhile(escriptParser.StatementWhileContext ctx) {
        return super.visitStatementWhile(ctx);
    }

    @Override
    public Object visitStatementFor(escriptParser.StatementForContext ctx) {
        return super.visitStatementFor(ctx);
    }

    @Override
    public Object visitStatementCondition(escriptParser.StatementConditionContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitStatementReturn(escriptParser.StatementReturnContext ctx) {
        return super.visitStatementReturn(ctx);
    }

    @Override
    public Object visitConditionComparison(escriptParser.ConditionComparisonContext ctx) {
        EComparisonOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.EQUALS:
                op = EComparisonOperator.EQUALS;
                break;
            case escriptParser.NOTEQUALS:
                op = EComparisonOperator.NOT_EQUALS;
                break;
            case escriptParser.GT:
                op = EComparisonOperator.GREATER_THEN;
                break;
            case escriptParser.GTE:
                op = EComparisonOperator.GREATER_THEN_OR_EQUALS;
                break;
            case escriptParser.LT:
                op = EComparisonOperator.LESS_THEN;
                break;
            case escriptParser.LTE:
                op = EComparisonOperator.LESS_THEN_OR_EQUALS;
                break;
        }

        return new ConditionNode(
                (ASTNode) visit(ctx.left),
                op,
                (ASTNode) visit(ctx.right)
        );
    }

    @Override
    public Object visitConditionValue(escriptParser.ConditionValueContext ctx) {
        return new BooleanNode(ctx.value.getType() == escriptParser.TRUE);
    }

    @Override
    public Object visitConditionExpression(escriptParser.ConditionExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitConditionParens(escriptParser.ConditionParensContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitConditionInverted(escriptParser.ConditionInvertedContext ctx) {
        // TODO: ADD inversion node?
        return new InvertNode(
                (ASTNode) visit(ctx.condition())
        );
    }

    @Override
    public Object visitConditionLogic(escriptParser.ConditionLogicContext ctx) {
        EComparisonOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.EQUALS:
                op = EComparisonOperator.EQUALS;
                break;
            case escriptParser.NOTEQUALS:
                op = EComparisonOperator.NOT_EQUALS;
                break;
            case escriptParser.AND:
                op = EComparisonOperator.AND;
                break;
            case escriptParser.OR:
                op = EComparisonOperator.OR;
                break;
        }

        return new ConditionNode(
                (ASTNode) visit(ctx.left),
                op,
                (ASTNode) visit(ctx.right)
        );
    }

    @Override
    public Object visitArray(escriptParser.ArrayContext ctx) {
        return super.visitArray(ctx);
    }

    @Override
    public Object visitExpressionValue(escriptParser.ExpressionValueContext ctx) {

        ASTNode node = null;
        if (ctx.ID() != null) {
            node = new VariableNode(ctx.val.getText());
        } else if (ctx.val.getText().contains(".")) {
            // Floating point
            // TODO: Create FloatNode
            node = new FloatNode(Float.valueOf(ctx.val.getText()));
        } else if (ctx.val.getType() == escriptParser.NUMBER) {
            // Integer
            node = new IntegerNode(Integer.valueOf(ctx.val.getText()));
        } else {
            node = new StringNode(ctx.val.getText());
        }

        if (ctx.SUB() != null) {
            return new InvertNode(node);
        }

        return node;
    }

    @Override
    public Object visitExpressionMath(escriptParser.ExpressionMathContext ctx) {
        EExpressionOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.POW:
                op = EExpressionOperator.POWER;
                break;
            case escriptParser.MUL:
                op = EExpressionOperator.MULTIPLY;
                break;
            case escriptParser.DIV:
                op = EExpressionOperator.DIVIDE;
                break;
            case escriptParser.MOD:
                op = EExpressionOperator.MODULUS;
                break;
            case escriptParser.ADD:
                op = EExpressionOperator.ADD;
                break;
            case escriptParser.SUB:
                op = EExpressionOperator.SUBTRACT;
                break;
        }

        return new ExpressionNode(
                (ASTNode) visit(ctx.left),
                op,
                (ASTNode) visit(ctx.right)
        );
    }

    @Override
    public Object visitExpressionParens(escriptParser.ExpressionParensContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx) {
        return new AssignmentNode(
                new VariableNode(ctx.ID().getText()),
                (ASTNode) visit(ctx.expression())
        );
    }
}
