package expressionscript.ast.nodes;

import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ASTNode<E> implements Tree {
    private E value;

    private ASTNode parent;
    private List<ASTNode> children;

    public ASTNode() {
        this.children = new ArrayList<>();
    }

    public ASTNode(List<ASTNode> children) {
        this.children = children;
//        if (children != null)
//            children.forEach(c -> c.setParent(this));
    }

    @Override
    public Tree getParent() {
        return parent;
    }

    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    @Override
    public Object getPayload() {
        return value;
    }

    public void setPayload(E payload) {
        value = payload;
    }

    @Override
    public Tree getChild(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    public void addChild(ASTNode child) {
        children.add(child);
        child.setParent(this);
    }

    @Override
    public String toStringTree() {
        return String.format("(%s %s)", getPayload(),
                children.stream().map(ASTNode::toStringTree).collect(Collectors.joining()));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
