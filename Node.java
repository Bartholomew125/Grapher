import java.util.ArrayList;
import java.util.List;
/**
 * The class Node is used to represent a node in a network/graph. This node has
 * some content, a title, and its parents and children.
 */
public class Node<T> {

    private String title;
    private List<Node<T>> parents;
    private List<Node<T>> children;
    private T content;

    public Node(String title, T content) {
        this.content = content;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * Return the title of the node.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Return the content of the node.
     */
    public T getContent() {
        return this.content;
    }

    /**
     * Add a node as a child to this node.
     */
    public void addChild(Node<T> node) {
        this.children.add(node);
    }

    /**
     * Add a node as a parent to this node.
     */
    public void addParent(Node<T> node) {
        this.parents.add(node);
    }

    /**
     * Return the children of this Node.
     */
    public List<Node<T>> getChildren() {
        return this.children;
    }

}
