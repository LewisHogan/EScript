package expressionscript;

import expressionscript.ast.nodes.ASTNode;

public class ReversePolishMaker {
    public String visit(ASTNode node) {
        if (node.getChildCount() == 2) {
            return String.format("%s %s %s", visit((ASTNode) node.getChild(0)),
                    visit((ASTNode)node.getChild(1)),
            node.getPayload());
        }

        return node.getPayload().toString();
    }
}
