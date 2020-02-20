package expressionscript.ast.nodes;

import java.util.List;

public class StartNode extends ASTNode {
    public StartNode(List<ASTNode> children) {
        setPayload("Start");
        if (children != null)
            children.forEach(this::addChild);
    }
}
