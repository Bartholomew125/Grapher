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
            boolean isParent = Math.random() < 0.5;
            Node node1 = nodes[(int) (Math.random() * numOfNodes)];
            Node node2 = nodes[(int) (Math.random() * numOfNodes)];

            if (isParent) {
                node2.addParent(node1);
            }
            else{
                node2.addChild(node1);
            }
        }

        this.addAllNodes(nodes);
        
    }
}
