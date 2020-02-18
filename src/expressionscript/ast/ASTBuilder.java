package expressionscript.ast;

import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.condition.*;
import expressionscript.ast.nodes.expression.*;
import expressionscript.escriptBaseVisitor;
import expressionscript.escriptParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Builder for an Abstract Syntax Tree
 */
public class ASTBuilder extends escriptBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStart(escriptParser.StartContext ctx) {
        ASTNode root = new ASTNode(ctx.statement().stream().map(this::visit).collect(Collectors.toList()));
        root.setPayload("Start");
        return root;
    }

    @Override
    public ASTNode visitStatementBlock(escriptParser.StatementBlockContext ctx) {
        // TODO: Implement StatementNode(List<IASTNode> elements) (elements is either Statement or Expression)
        if (ctx.statement().size() > 1) {
            ASTNode block = new ASTNode();
            block.setPayload("Block");
            ctx.statement().stream().map(this::visit).forEach(n -> block.addChild(n));
            return block;
        }

        // TODO: What happens if there are no statements?
        // Return an empty node or just return null and discard in the calling visit?
        // If we can return null then that means we'll need to check for null at every point
        // that can contain a statement

        return visit(ctx.statement(0));
    }

    @Override
    public ASTNode visitStatementAssignment(escriptParser.StatementAssignmentContext ctx) {
        return new AssignmentNode(
                new VariableNode(ctx.ID().getText()),
                visit(ctx.condition() != null ? ctx.condition() : ctx.expression())
        );
    }

    @Override
    public ASTNode visitStatementBranch(escriptParser.StatementBranchContext ctx) {
        IfNode ifNode = new IfNode(
                (ConditionNode) visit(ctx.condition(0)),
                visit(ctx.statement(0))
        );

        List<IfNode> elseIfNodes = new ArrayList<>();
        for (int i = 0; i < ctx.ELSEIF().size(); i++) {
            IfNode elseIfNode = new IfNode(
                    (ConditionNode) visit(ctx.condition(1 + i)),
                    visit(ctx.statement(1 + i))
            );

            elseIfNodes.add(elseIfNode);
        }

        if (ctx.ELSE() != null) {
            return new BranchNode(
                    ifNode,
                    elseIfNodes,
                    visit(ctx.statement(ctx.statement().size() - 1))
            );
        }

        return new BranchNode(
                ifNode,
                elseIfNodes
        );
    }

    @Override
    public ASTNode visitStatementWhile(escriptParser.StatementWhileContext ctx) {
        // TODO: Implement WhileNode(ConditionNode, StatementBlockNode)
        return super.visitStatementWhile(ctx);
    }

    @Override
    public ASTNode visitStatementFor(escriptParser.StatementForContext ctx) {
        // TODO: Implement ForNode(StatementNode, ConditionNode, StatementNode)
        return super.visitStatementFor(ctx);
    }

    @Override
    public ASTNode visitStatementCondition(escriptParser.StatementConditionContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public ASTNode visitStatementReturn(escriptParser.StatementReturnContext ctx) {
        // TODO: Implement ReturnNode
        return super.visitStatementReturn(ctx);
    }

    @Override
    public ASTNode visitConditionComparison(escriptParser.ConditionComparisonContext ctx) {
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

        return new ConditionNode(visit(ctx.left), op, visit(ctx.right));
    }

    @Override
    public ASTNode visitConditionValue(escriptParser.ConditionValueContext ctx) {
        return new BooleanNode(ctx.value.getType() == escriptParser.TRUE);
    }

    @Override
    public ASTNode visitConditionExpression(escriptParser.ConditionExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitConditionParens(escriptParser.ConditionParensContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public ASTNode visitConditionInverted(escriptParser.ConditionInvertedContext ctx) {
        // TODO: Implement ConditionNode(ExpressionNode left, ComparisonOperator, ExpressionNode right)
        return super.visitConditionInverted(ctx);
    }

    @Override
    public ASTNode visitConditionLogic(escriptParser.ConditionLogicContext ctx) {
        EComparisonOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.AND:
                op = EComparisonOperator.AND;
                break;
            case escriptParser.OR:
                op = EComparisonOperator.OR;
                break;
            case escriptParser.EQUALS:
                op = EComparisonOperator.EQUALS;
                break;
            case escriptParser.NOTEQUALS:
                op = EComparisonOperator.NOT_EQUALS;
                break;
        }

        return new ConditionNode(visit(ctx.left), op, visit(ctx.right));
    }

    @Override
    public ASTNode visitArray(escriptParser.ArrayContext ctx) {
        // TODO: Implement ArrayNode(List<IASTNode> values)
        return super.visitArray(ctx);
    }

    @Override
    public ASTNode visitExpressionValue(escriptParser.ExpressionValueContext ctx) {
        // TODO: Implement StringNode(value) IntNode(Value), FloatNode(Value), ArrayNode(List<IASTNode> values)
        ASTNode valueNode = null;
        switch (ctx.val.getType()) {
            case escriptParser.STRING:
                valueNode = new StringNode(ctx.val.getText()); // currently includes "quotes"
                break;
            case escriptParser.ID:
                valueNode = new VariableNode(ctx.val.getText());
                break;
            case escriptParser.NUMBER:
                return new VariableNode(ctx.val.getText()); // TODO: Fix this to actually use number nodes and values
        }

        return valueNode;
    }

    @Override
    public ASTNode visitExpressionMath(escriptParser.ExpressionMathContext ctx) {
        EExpressionOperator op = null;

        switch (ctx.op.getType()) {
            case escriptParser.ADD:
                op = EExpressionOperator.ADD;
                break;
            case escriptParser.SUB:
                op = EExpressionOperator.SUBTRACT;
                break;
            case escriptParser.DIV:
                op = EExpressionOperator.DIVIDE;
                break;
            case escriptParser.MUL:
                op = EExpressionOperator.MULTIPLY;
                break;
            case escriptParser.MOD:
                op = EExpressionOperator.MODULUS;
                break;
        }

        return new ExpressionNode(
                visit(ctx.left),
                op,
                visit(ctx.right)
        );
    }

    @Override
    public ASTNode visitExpressionParens(escriptParser.ExpressionParensContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx) {
        return new AssignmentNode(new VariableNode(ctx.ID().getText()), visit(ctx.expression()));
    }

    @Override
    public ASTNode visitExpressionArray(escriptParser.ExpressionArrayContext ctx) {
        // TODO: Implement ArrayNode(List<IASTNode> values)
        return super.visitExpressionArray(ctx);
    }
}
