package ast.nodes;

import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class to represent a generic Abstract Syntax Tree Node.
 *
 * @param <E> The type of payload value.
 */
public class ASTNode<E> implements Tree {
    // The item that will be displayed in the TreeViewer
    private E payload;

    // To ensure the TreeViewer works correctly, each node must have a reference to their parent
    // otherwise the spacing between nodes breaks for some reason.
    private ASTNode parent;
    private List<ASTNode> children = new ArrayList<>();

    public ASTNode(List<ASTNode> children) {
        if (children != null)
            children.forEach(this::addChild);
    }

    public ASTNode() {

    }

    @Override
    public Tree getParent() {
        return parent;
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    @Override
    public Tree getChild(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public String toStringTree() {
        return String.format("(%s %s)", getPayload(),
                children.stream().map(ASTNode::toStringTree).collect(Collectors.joining()));
    }

    public void addChild(ASTNode child) {
        children.add(child);
    }

    public void setPayload(E payload) {
        this.payload = payload;
    }

    public void setParent(ASTNode parent) {
        this.parent = parent;
    }
}
