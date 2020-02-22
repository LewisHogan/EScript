package escript.ast;

import escript.ast.nodes.ASTNode;
import escript.ast.nodes.StartNode;
import escript.ast.nodes.condition.BranchNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.condition.IfNode;
import escript.ast.nodes.statement.*;
import escript.ast.nodes.values.*;
import escript.escriptBaseVisitor;
import escript.escriptParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to construct an Abstract Syntax Tree from the Concrete Syntax Tree produced by Antlr.
 */
public class ASTBuilder extends escriptBaseVisitor {
    @Override
    public Object visitStart(escriptParser.StartContext ctx) {
        return new StartNode(
                ctx.statement().stream().map(this::visit).map(n -> (ASTNode) n).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitStatementBlock(escriptParser.StatementBlockContext ctx) {
        // As we return a List<ASTNode> here, rather than a ASTNode, we cannot use escriptBaseVisitor<ASTNode>
        return ctx.statement().stream().map(this::visit).collect(Collectors.toList());
    }

    @Override
    public Object visitStatementAssignment(escriptParser.StatementAssignmentContext ctx) {
        return new AssignmentNode(new VariableNode(ctx.ID().getText()),
                (ASTNode) visit(ctx.condition() != null ? ctx.condition() : ctx.expression())
        );
    }

    /**
     * Ensures that any node being passed in here is returned as a list, useful for IfNode construction.
     *
     * @param nodes The node(s) that needs to be returned as a list.
     * @return A list of the nodes.
     */
    private List ensureList(Object nodes) {
        // This is needed b
        if (nodes instanceof List) return (List) nodes;
        return Arrays.asList(nodes);
    }

    /**
     * Creates an IfNode from the condition and statement contexts provided.
     *
     * @param condition A condition context.
     * @param statement A statement context.
     * @return An IfNode encompassing the data stored in both contexts.
     */
    private IfNode buildIfNode(escriptParser.ConditionContext condition, escriptParser.StatementContext statement) {
        List statements = ensureList(visit(statement));

        return new IfNode(
                (ConditionNode) visit(condition),
                statements
        );
    }

    @Override
    public Object visitStatementBranch(escriptParser.StatementBranchContext ctx) {

        List<IfNode> elseIfNodes = new ArrayList<>();
        for (int i = 0; i < ctx.ELSEIF().size(); i++)
            elseIfNodes.add(buildIfNode(ctx.condition(i + 1), ctx.statement(i + 1)));

        List elseStatements = null;
        if (ctx.ELSE() != null) {
            // The last statement should be the else statement in this case
            // Even if it is a statement block, just visiting should be enough
            // In that event though, it will already be a list
            elseStatements = ensureList(visit(ctx.statement(ctx.statement().size() - 1)));
        }

        return new BranchNode(buildIfNode(ctx.condition(0), ctx.statement(0)), elseIfNodes, elseStatements);
    }

    @Override
    public Object visitStatementWhile(escriptParser.StatementWhileContext ctx) {
        List whileStatements = ensureList(visit(ctx.statement()));

        return new WhileNode(
                new ConditionNode(
                        (ASTNode) visit(ctx.condition())
                ),
                whileStatements
        );
    }

    @Override
    public Object visitStatementFor(escriptParser.StatementForContext ctx) {
        List forStatements = ensureList(visit(ctx.statement(1)));
        return new ForNode(
                (AssignmentNode) visit(ctx.statement(0)),
                (ConditionNode) visit(ctx.condition()),
                (ASTNode) visit(ctx.expression()),
                forStatements
        );
    }

    @Override
    public Object visitStatementPrint(escriptParser.StatementPrintContext ctx) {
        ASTNode node = (ASTNode) visit(ctx.condition());
        if (node.getChildCount() == 1) return new PrintNode((ASTNode) node.getChild(0));

        return new PrintNode((ASTNode) visit(ctx.condition()));
    }

    @Override
    public Object visitStatementCondition(escriptParser.StatementConditionContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitConditionComparison(escriptParser.ConditionComparisonContext ctx) {
        EComparisonOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.EQUALS:
                op = EComparisonOperator.EQUALS;
                break;
            case escriptParser.NOT_EQUALS:
                op = EComparisonOperator.NOT_EQUALS;
                break;
            case escriptParser.GREATER_THAN:
                op = EComparisonOperator.GREATER_THAN;
                break;
            case escriptParser.GREATER_THAN_OR_EQUALS:
                op = EComparisonOperator.GREATER_THAN_OR_EQUALS;
                break;
            case escriptParser.LESS_THAN:
                op = EComparisonOperator.LESS_THAN;
                break;
            case escriptParser.LESS_THAN_OR_EQUALS:
                op = EComparisonOperator.LESS_THAN_OR_EQUALS;
                break;
        }

        return new ConditionNode(
                (ASTNode) visit(ctx.left),
                op,
                (ASTNode) visit(ctx.right)
        );
    }

    @Override
    public Object visitConditionExpression(escriptParser.ConditionExpressionContext ctx) {
        return new ConditionNode((ASTNode) visit(ctx.expression()));
    }

    @Override
    public Object visitConditionInverted(escriptParser.ConditionInvertedContext ctx) {
        return new ConditionNode(new InversionNode((ASTNode) visit(ctx.condition())));
    }

    @Override
    public Object visitConditionLogic(escriptParser.ConditionLogicContext ctx) {
        EComparisonOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.EQUALS:
                op = EComparisonOperator.EQUALS;
                break;
            case escriptParser.NOT_EQUALS:
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
    public Object visitConditionParenthesis(escriptParser.ConditionParenthesisContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitExpressionBinary(escriptParser.ExpressionBinaryContext ctx) {
        EExpressionOperator op = null;
        switch (ctx.op.getType()) {
            case escriptParser.POW:
                op = EExpressionOperator.POWER;
                break;
            case escriptParser.MOD:
                op = EExpressionOperator.MODULO;
                break;
            case escriptParser.MUL:
                op = EExpressionOperator.MULTIPLY;
                break;
            case escriptParser.DIV:
                op = EExpressionOperator.DIVIDE;
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
    public Object visitExpressionValue(escriptParser.ExpressionValueContext ctx) {
        ASTNode node = null;
        if (ctx.ID() != null) {
            node = new VariableNode(ctx.val.getText());
        } else if (ctx.val.getText().contains(".") && ctx.val.getType() == escriptParser.NUMBER) {
            node = new FloatNode(Float.valueOf(ctx.val.getText()));
        } else if (ctx.val.getType() == escriptParser.NUMBER) {
            node = new IntegerNode(Integer.valueOf(ctx.val.getText()));
        }

        if (ctx.SUB() != null) {
            return new InversionNode(node);
        }

        if (ctx.NOT() != null) {
            return new InversionNode(node, true);
        }

        return node;
    }

    @Override
    public Object visitExpressionBoolean(escriptParser.ExpressionBooleanContext ctx) {
        BooleanNode node = new BooleanNode(ctx.val.getType() == escriptParser.TRUE);
        return ctx.NOT() != null ? new InversionNode(node) : node;
    }

    @Override
    public Object visitExpressionParenthesis(escriptParser.ExpressionParenthesisContext ctx) {
        ASTNode node = (ASTNode) visit(ctx.condition() != null ? ctx.condition() : ctx.expression());
        if (ctx.SUB() != null || ctx.NOT() != null) {
            return new InversionNode(node);
        }

        return node;
    }

    @Override
    public Object visitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx) {
        return new AssignmentNode(new VariableNode(ctx.ID().getText()), (ASTNode) visit(ctx.expression()));
    }

    @Override
    public Object visitExpressionString(escriptParser.ExpressionStringContext ctx) {
        return new StringNode(ctx.val.getText());
    }

    @Override
    public Object visitExpressionModSetVar(escriptParser.ExpressionModSetVarContext ctx) {
        System.out.println("OP: " + ctx.op.getText());
        return super.visitExpressionModSetVar(ctx);
    }
}
