package grapher;
import java.util.ArrayList;
import java.util.List;
/**
 * The class Node is used to represent a node in a network/graph. This node has
 * some content, a title, and its parents and children.
 */
public class Node {

    private List<Node> parents;
    private List<Node> children;
    private NodeContent content;

    public Node(NodeContent content) {
        this.content = content;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * Return the content of the node.
     */
    public NodeContent getContent() {
        return this.content;
    }

    /**
     * Add a node as a child to this node.
     */
    public void addChild(Node node) {
        this.children.add(node);
    }

    /**
     * Add a node as a parent to this node.
     */
    public void addParent(Node node) {
        this.parents.add(node);
    }

    /**
     * Return the children of this Node.
     */
    public List<Node> getChildren() {
        return this.children;
    }

    /**
     * Return the parents of this Node.
     */
    public List<Node> getParents() {
        return this.parents;
    }

    @Override
    public boolean equals(Object obj) {
        Node other = (Node) obj;
        if (this.getContent().toString().equals(other.getContent().toString())) {
            return true;
        }
        return false;
    }
}
