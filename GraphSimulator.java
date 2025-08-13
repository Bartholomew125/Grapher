import java.util.List;

/**
 * The class GraphSimulator is used to simulate how all the SimulationNodes are
 * suppose to move and interact.
 */
public class GraphSimulator {

    private SimulationNode[] simNodes;

    public GraphSimulator(Network network) {
        // First create a SimulationNode for each Node.
        this.simNodes = new SimulationNode[network.size()];
        for (int i = 0; i < this.simNodes.length; i++) {
            Node node = network.getNodes().get(i);
            double x = Math.random();
            double y = Math.random();
            float r = 0.0f;
            float g = 0.0f;
            float b = 0.0f;
            this.simNodes[i] = new SimulationNode(node,x,y,r,g,b);
        }

        // Map the parents and children of the Nodes in the SimulationNodes, to
        // the parents and children of the SimulationNodes.
        for (SimulationNode simNode : this.simNodes) {
            Node node = simNode.getNode();

            // Chech the children
            List<Node> children = node.getChildren();
            for (Node child : children) {
                for (SimulationNode otherSimNode : this.simNodes) {
                    if (otherSimNode.getNode().equals(child)) {
                        simNode.addChild(otherSimNode);
                    }
                }
            }

            // Check the parents
            List<Node> parents = node.getParents();
            for (Node parent : parents) {
                for (SimulationNode otherSimNode : this.simNodes) {
                    if (otherSimNode.getNode().equals(parent)) {
                        simNode.addParent(otherSimNode);
                    }
                }
            }
        }
    }

    /**
     * Updates all the nodes in the simulation, and determines how they are
     * suppose to move.
     */
    public void update() {
        // Go through each pair of nodes only once.
        for (int i = 0; i < this.simNodes.length-1; i++) {
            for (int j = i+1; j < this.simNodes.length; j++) {

                SimulationNode node1 = this.simNodes[i];
                SimulationNode node2 = this.simNodes[j];

                if (node1 != node2) {
                    // Node positions
                    Vector p1 = new Vector(node1.getX(), node1.getY());
                    Vector p2 = new Vector(node2.getX(), node2.getY());

                    // Vector from node1 to node 2
                    Vector dir = p2.subtract(p1);
                    
                    // Calculate force. Repel when close and attract when far.
                    double force = 0.000001 * 1/dir.getLength();


                    if (dir.getLength() < 0.3) {
                        // Normalize the direction vector and scale by force
                        dir.normalize();
                        dir.scale(force);

                        // Apply to node 2
                        node2.applyForce(dir);

                        // Reverse and apply to node1
                        dir.scale(-1);
                        node1.applyForce(dir);
                    }
                    else {
                        // Normalize the direction vector and scale by force
                        dir.normalize();
                        dir.scale(force);

                        // Apply to node1
                        node1.applyForce(dir);

                        // Reverse and apply to node2
                        dir.scale(-1);
                        node2.applyForce(dir);
                    }
                }
            }
        }

        // Update Positions at once.
        for (SimulationNode node : this.simNodes) {
            node.updatePosition();
        }
    }

    /**
     * Return the number of nodes in this simulation.
     */
    public int nodeCount() {
        return this.simNodes.length;
    }

    /**
     * Return the nodes of this simulation.
     */
    public SimulationNode[] getSimNodes() {
        return this.simNodes;
    }

}
