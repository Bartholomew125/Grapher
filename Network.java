import java.util.List;
import java.util.ArrayList;

/**
 * The class Network is used to represent a collection of nodes, and has methods
 * to add new nodes.
 */
public class Network<T> {

    private List<Node<T>> nodes;

    /**
     * Initialize a new empty network.
     */
    public Network() {
        this.nodes = new ArrayList<>();
    }

    /**
     * Add a single node to the network.
     */
    public void addNode(Node<T> node) {
        this.nodes.add(node);
    }

    /**
     * Add an array of nodes to the network.
     */
    public void addAllNodes(Node<T>[] nodes) {
        for (Node<T> node : nodes) {
            this.addNode(node);
        }
    }

    /**
     * Get the size of the network.
     */
    public int size() {
        return this.nodes.size();
    }

    /**
     * Get a pointer to the list of nodes in the network.
    */
    public List<Node<T>> getNodes() {
        return this.nodes;
    }
}
