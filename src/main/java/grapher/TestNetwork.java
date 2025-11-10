package grapher;
/**
 * The class TestNetwork is an example network which just has a bunch of nodes
 * and connections.
 */
public class TestNetwork extends Network {
    
    public TestNetwork(int numOfNodes) {
        super();
        Node[] nodes = new Node[numOfNodes];
        for (int i = 0; i < numOfNodes; i++) {
            nodes[i] = new Node(new TestContent());
        }

        int iterations = numOfNodes;
        for (int i = 0; i < iterations; i++) {
            Node node1 = nodes[(int) (Math.random() * numOfNodes)];
            Node node2 = nodes[(int) (Math.random() * numOfNodes)];

            if (node1 != node2) {
                node2.addParent(node1);
                node1.addChild(node2);
            }
        }

        this.addAllNodes(nodes);
        
    }
}
