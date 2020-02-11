package expressionscript;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EvaluationVisitor extends escriptBaseVisitor {

    // Improvement to add once variables are properly implemented
    // Scope implemented as Tree, with each node being a map of variables?
    // TreeNode root
    // TreeNode current = root;

    // to go to new scope
    // current = current.addNewNode();

    // to exit scope
    // current = current.parent;

    // to fetch variable (including ones in parent scope
    // Object variable = current.findVariable(String name)

//    private Object findVariable(String name) {
//
//        if (current.variables.contains(name))
//            return current.variables.get(name);
//
//        // We do not want to search sibling scopes
//        if (current != root) return current.parent.findVariable(name);
//
//        return null;
//    }

    // temporary global symbol table
    private Map<String, Object> symbolTable = new HashMap<>();

    @Override
    public Object visitStart(escriptParser.StartContext ctx) {
        return ctx.statement().stream().map(this::visit).map(o -> {
            if (o instanceof Integer || o instanceof Float || o instanceof Boolean)
                return o.toString();
            else
                return (String) o;
        }).collect(Collectors.joining("\n"));
    }

    @Override
    public Object visitStatementBlock(escriptParser.StatementBlockContext ctx) {
        return super.visitStatementBlock(ctx);
    }

    @Override
    public Object visitStatementAssignment(escriptParser.StatementAssignmentContext ctx) {
        Object result = visit(ctx.expression());
        setVariableInScope(ctx.ID().getText(), result);
        return result;
    }

    @Override
    public Object visitStatementBranch(escriptParser.StatementBranchContext ctx) {
        Object ifCondition = visit(ctx.condition(0));
        if (ifCondition != null && ((Boolean) ifCondition)) {
            return visit(ctx.statement(0));
        }

        for (int i = 0; i < ctx.ELSEIF().size(); i++) {
            Object elseIfCondition = visit(ctx.condition(i + 1));

            if (elseIfCondition != null && ((Boolean) elseIfCondition)) {
                return visit(ctx.statement(i+1));
            }
        }

        if (ctx.ELSE() != null) {
            return visit(ctx.statement(ctx.statement().size() - 1));
        }

        return null;
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
        Object left = visit(ctx.left);
        Object right = visit(ctx.right);

        // TODO: Find a cleaner way
        switch (ctx.op.getType()) {
            case escriptParser.GT:
                if (left instanceof Number && right instanceof Number)
                    return ((Number) left).floatValue() > ((Number) right).floatValue();
                break;
            case escriptParser.GTE:
                if (left instanceof Number && right instanceof Number)
                    return ((Number) left).floatValue() >= ((Number) right).floatValue();
                break;
            case escriptParser.LT:
                if (left instanceof Number && right instanceof Number)
                    return ((Number) left).floatValue() < ((Number) right).floatValue();
                break;
            case escriptParser.LTE:
                if (left instanceof Number && right instanceof Number)
                    return ((Number) left).floatValue() <= ((Number) right).floatValue();
                break;
            case escriptParser.EQUALS:
                return left.equals(right);
        }
        return super.visitConditionComparison(ctx);
    }

    @Override
    public Object visitConditionValue(escriptParser.ConditionValueContext ctx) {
        return super.visitConditionValue(ctx);
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
        return super.visitConditionInverted(ctx);
    }

    @Override
    public Object visitConditionLogic(escriptParser.ConditionLogicContext ctx) {
        return super.visitConditionLogic(ctx);
    }

    @Override
    public Object visitExpressionValue(escriptParser.ExpressionValueContext ctx) {
        // TODO: Stop all variables being considered as 0

        if (ctx.NUMBER() != null || ctx.ID() != null) {
            if (ctx.val.getText().contains(".")) {
                float value = ctx.NUMBER() != null ?
                        Float.valueOf(ctx.NUMBER().getText())
                        : getVariableInScope(ctx.val.getText());
                return ctx.SUB() != null ? -value : value;
            } else {
                Integer value = ctx.NUMBER() != null ?
                        Integer.valueOf(ctx.NUMBER().getText())
                        : getVariableInScope(ctx.val.getText());
                return ctx.SUB() != null ? -value : value;
            }
        } else {
            // We have a string, just return the string
            return ctx.val.getText();
        }
    }

    @Override
    public Object visitExpressionMath(escriptParser.ExpressionMathContext ctx) {
        Number left = (Number) visit(ctx.left);
        Number right = (Number) visit(ctx.right);

        if (left instanceof Integer) {
            if (right instanceof Integer) {
                switch (ctx.op.getType()) {
                    case escriptParser.ADD:
                        return left.intValue() + right.intValue();
                    case escriptParser.SUB:
                        return left.intValue() - right.intValue();
                    case escriptParser.MUL:
                        return left.intValue() * right.intValue();
                    case escriptParser.DIV:
                        return left.intValue() / right.intValue();
                }
            } else if (right instanceof Float) {
                switch (ctx.op.getType()) {
                    case escriptParser.ADD:
                        return left.intValue() + right.floatValue();
                    case escriptParser.SUB:
                        return left.intValue() - right.floatValue();
                    case escriptParser.MUL:
                        return left.intValue() * right.floatValue();
                    case escriptParser.DIV:
                        return left.intValue() / right.floatValue();
                }
            }
        } else if (left instanceof Float) {
            if (right instanceof Integer) {
                switch (ctx.op.getType()) {
                    case escriptParser.ADD:
                        return left.floatValue() + right.intValue();
                    case escriptParser.SUB:
                        return left.floatValue() - right.intValue();
                    case escriptParser.MUL:
                        return left.floatValue() * right.intValue();
                    case escriptParser.DIV:
                        return left.floatValue() / right.intValue();
                }
            } else if (right instanceof Float) {
                switch (ctx.op.getType()) {
                    case escriptParser.ADD:
                        return left.floatValue() + right.floatValue();
                    case escriptParser.SUB:
                        return left.floatValue() - right.floatValue();
                    case escriptParser.MUL:
                        return left.floatValue() * right.floatValue();
                    case escriptParser.DIV:
                        return left.floatValue() / right.floatValue();
                }
            }
        }

        return null;
    }

    @Override
    public Object visitExpressionParens(escriptParser.ExpressionParensContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx) {
        // TODO: Set variable ID to value, return value
        Object result = visit(ctx.expression());
        setVariableInScope(ctx.ID().getText(), result);
        return result;
    }

    private <E> E getVariableInScope(String variable) {
        if (symbolTable.containsKey(variable)) return (E) symbolTable.get(variable);
        return null;
    };

    private <E> void setVariableInScope(String variable, E value) {
        System.out.println("Setting " + variable);
        symbolTable.put(variable, value);
    }
}
