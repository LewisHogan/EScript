import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import escript.ast.nodes.StartNode;
import escript.ast.nodes.condition.BranchNode;
import escript.ast.nodes.condition.ConditionNode;
import escript.ast.nodes.condition.EComparisonOperator;
import escript.ast.nodes.condition.IfNode;
import escript.ast.nodes.statement.*;
import escript.ast.nodes.statement.payload.ForPayload;
import escript.ast.nodes.statement.payload.WhilePayload;
import escript.ast.nodes.values.*;

/**
 * Visits an abstract syntax tree and creates nicely formatted code from it.
 */
public class OldPrettyPrintVisitor extends escript.ast.ASTVisitor<String> {

    private int indentationLevel = 0;

    /**
     * Indent utility function that returns the specified number of tab characters.
     *
     * @param n The number of tab characters to generate.
     * @return A string of tab characters.
     */
    private String indent(int n) {
        String output = "";
        for (int i = 0; i < n; i++) output += "\t";
        return output;
    }

    /**
     * Utility function that helps determine if a semi colon is going to be necessary for formatting.
     *
     * @param node The node to check if it needs a semi colon
     * @return If the node type should have a semi colon after the end
     */
    private boolean isSemiColonNeeded(ASTNode node) {
        return !(node instanceof IfNode || node instanceof WhileNode
                || node instanceof ForNode || node instanceof BranchNode
                || node instanceof BlockNode
        );
    }

    @Override
    protected String visitStart(StartNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String output = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            ASTNode child = (ASTNode) node.getChild(i);
            output += visit(child) + (isSemiColonNeeded(child) ? ";" : "");// + ((child instanceof WhileNode || child instanceof BranchNode) ? "" : ";");
            if (i != node.getChildCount() - 1) output += "\n";
        }
        return output;
    }

    @Override
    protected String visitBranch(BranchNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        boolean isElsePresent = false;
        int ifNodeCount;
        String output = "";
        for (ifNodeCount = 0; ifNodeCount < node.getChildCount(); ifNodeCount++) {
            if (!(node.getChild(ifNodeCount) instanceof IfNode)) {
                isElsePresent = true;
                break;
            }

            output += visit((ASTNode) node.getChild(ifNodeCount));
        }

        if (isElsePresent) {
            // If there are multiple nodes left, we need to go into another block
            boolean multipleElseStatements = (node.getChildCount() - ifNodeCount) > 1;

            if (multipleElseStatements) {
                String children = "";
                for (int i = ifNodeCount; i < node.getChildCount(); i++) {
                    // If children are not branch or block nodes, we'll need to add a semi colon to the end
                    ASTNode child = (ASTNode) node.getChild(i);
                    children += visit(child) + (isSemiColonNeeded(child) ? ";" : "");
                    if (i < node.getChildCount() - 1) children += "\n";
                }

                output += String.format(
                        "else {\n%s\n}\n",
//                        indent(++indentationLevel),
                        children
//                        indent(--indentationLevel)
                );
            } else {
                ASTNode child = (ASTNode) node.getChild(node.getChildCount() - 1);
                output += "else " + visit(child) + (isSemiColonNeeded(child) ? ";" : "");
            }
        }

        return output;
    }

    @Override
    protected String visitBlock(BlockNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String body = "";
        for (int i = 0; i < node.getChildCount(); i++) {
            body += visit((ASTNode) node.getChild(i));
        }
        return body;
    }

    @Override
    protected String visitCondition(ConditionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (node.getChildCount() == 1) return String.format("%s", visit((ASTNode) node.getChild(0)));

        String output = String.format("%s %s %s",
                visit((ASTNode) node.getChild(0)), node.getPayload(), visit((ASTNode) node.getChild(1))
        );

        if (node.getParent() instanceof ConditionNode) {
            // If the parent has an operator which is higher priority, we need to print parenthesis, or order priority
            // will be broken
            boolean needsParenthesis = ((EComparisonOperator) node.getParent().getPayload()).
                    isPriority((EComparisonOperator) node.getPayload());

            if (needsParenthesis)
                return String.format("(%s)", output);
        }

        return output;
    }

    @Override
    protected String visitIf(IfNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        boolean hasMultipleStatements = node.getChildCount() > 1;
        boolean isElseIf = node.getParent().getChild(0) != node;

        if (hasMultipleStatements) {
            String children = "";
            indentationLevel++;
            for (int i = 0; i < node.getChildCount(); i++) {
                ASTNode child = (ASTNode) node.getChild(i);
                children += indent(indentationLevel) + visit(child) + (isSemiColonNeeded(child) ? ";" : "");
                if (i < node.getChildCount() - 1) children += "\n";
            }
            indentationLevel--;

            String output =

             String.format("%sif (%s) {\n%s\n%s}\n",
                    isElseIf ? "else " : "",
                    visit((ASTNode) node.getPayload()),
                    children,
                     indent(indentationLevel)
            );

            return output;
        }

        ASTNode child = (ASTNode) node.getChild(0);

        return String.format("%sif (%s) %s\n",
                isElseIf ? "else " : "",
                visit((ASTNode) node.getPayload()),
                visit(child) + (isSemiColonNeeded(child) ? ";" : "")
        );
    }

    @Override
    protected String visitAssignment(AssignmentNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("%s = %s", visit((ASTNode) node.getChild(0)), visit((ASTNode) node.getChild(1)));
    }

    @Override
    protected String visitExpression(ExpressionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        String output = String.format("%s %s %s",
                visit((ASTNode) node.getChild(0)),
                node.getPayload(),
                visit((ASTNode) node.getChild(1))
        );

        if (node.getParent() instanceof ExpressionNode) {
            // If we have a parent with a higher priority, then we need to put parenthesis in order to keep order of
            // operations the same
            boolean needsParenthesis = ((EExpressionOperator) node.getParent().getPayload()).isPriority(
                    (EExpressionOperator) node.getPayload()
            );

            if (needsParenthesis)
                return String.format("(%s)", output);
        }

        return output;
    }

    @Override
    protected String visitFor(ForNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        boolean hasMultipleStatements = node.getChild(0) instanceof BlockNode;
        ForPayload payload = (ForPayload) node.getPayload();

        if (hasMultipleStatements) {
            BlockNode block = (BlockNode) node.getChild(0);
            String children = "";
            for (int i = 0; i < block.getChildCount(); i++) {
                // TODO: May need to check if children are things which don't need ;, like while loops or if nodes
                ASTNode child = (ASTNode) block.getChild(i);
                children += indent(++indentationLevel) + visit(child) + (isSemiColonNeeded(child) ? ";" : "");
                if (i < block.getChildCount() - 1) children += "\n";
                indentationLevel--;
            }

            return String.format("%sfor (%s; %s; %s) {\n%s\n%s}\n",
                    indent(indentationLevel++),
                    visit(payload.getInitial()),
                    visit(payload.getCondition()),
                    visit(payload.getNext()),
                    children, // TODO Also need if ; necessary here in all cases
                    indent(--indentationLevel)
            );
        }

        ASTNode child = (ASTNode) node.getChild(0);

        return String.format("%sfor (%s; %s; %s) %s\n",
                indent(indentationLevel),
                visit(payload.getInitial()),
                visit(payload.getCondition()),
                visit(payload.getNext()),
                visit(child) + (isSemiColonNeeded(child) ? ";" : "")
        );
    }

    @Override
    protected String visitInversion(InversionNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        if (node.getPayload().toString().startsWith("!"))
            return String.format("!%s", visit((ASTNode) node.getChild(0)));

        return String.format("-%s", visit((ASTNode) node.getChild(0)));
    }

    @Override
    protected String visitPrint(PrintNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return String.format("print(%s)", visit((ASTNode) node.getChild(0)));
    }

    @Override
    protected String visitWhile(WhileNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        boolean hasMultipleStatements = node.getChildCount() > 1;

        WhilePayload payload = (WhilePayload) node.getPayload();

        if (hasMultipleStatements) {
            String children = "";
            indentationLevel++;
            for (int i = 0; i < node.getChildCount(); i++) {
                // TODO: May need to check if children are things which don't need ;, like while loops or if nodes
                ASTNode child = (ASTNode) node.getChild(i);
                children += indent(indentationLevel) + visit(child) + (isSemiColonNeeded(child) ? ";" : "");
                if (i < node.getChildCount() - 1) children += "\n";
            }
            indentationLevel--;

            return String.format("%swhile (%s) {\n%s\n%s}\n",
                    indent(indentationLevel),
                    visit(payload.getCondition()),
                    children, // TODO Also need if ; necessary here in all cases
                    indent(indentationLevel)
            );
        }

        ASTNode child = (ASTNode) node.getChild(0);
        return String.format("%swhile (%s) %s\n",
                indent(indentationLevel),
                visit(payload.getCondition()),
                visit(child) + (isSemiColonNeeded(child) ? ";" : "") // TODO Also need if ; necessary here in all cases
        );
    }

    @Override
    protected String visitBoolean(BooleanNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitFloat(FloatNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitInteger(IntegerNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitString(StringNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }

    @Override
    protected String visitVariable(VariableNode node) throws InvalidOperationException, UndefinedVariableException, InvalidIDException {
        return node.getPayload().toString();
    }
}
