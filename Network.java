import java.util.List;
import java.util.ArrayList;

/**
 * The class Network is used to represent a collection of nodes, and has methods
 * to add new nodes.
 */
public class Network {

    private List<Node> nodes;

    /**
     * Initialize a new empty network.
     */
    public Network() {
        this.nodes = new ArrayList<>();
    }

    /**
     * Add a single node to the network.
     */
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    /**
     * Add an array of nodes to the network.
     */
    public void addAllNodes(Node[] nodes) {
        for (Node node : nodes) {
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
    public List<Node> getNodes() {
        return this.nodes;
    }
}
