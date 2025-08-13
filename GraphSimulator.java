/**
 * The class GraphSimulator is used to simulate how all the SimulationNodes are
 * suppose to move and interact.
 */
public class GraphSimulator {

    private SimulationNode[] nodes;

    public GraphSimulator(Network<?> network) {
        this.nodes = new SimulationNode[network.size()];
        for (int i = 0; i < this.nodes.length; i++) {
            Node<?> node = network.getNodes().get(i);
            double x = Math.random();
            double y = Math.random();
            float r = 0.0f;
            float g = 0.0f;
            float b = 0.0f;
            this.nodes[i] = new SimulationNode(node,x,y,r,g,b);
        }
    }

    /**
     * Updates all the nodes in the simulation, and determines how they are
     * suppose to move.
     */
    public void update() {
        // Go through each pair of nodes only once.
        for (int i = 0; i < this.nodes.length-1; i++) {
            for (int j = i+1; j < this.nodes.length; j++) {

                SimulationNode node1 = this.nodes[i];
                SimulationNode node2 = this.nodes[j];

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
        for (SimulationNode node : this.nodes) {
            node.updatePosition();
        }
    }

    /**
     * Return the number of nodes in this simulation.
     */
    public int nodeCount() {
        return this.nodes.length;
    }

    /**
     * Return the nodes of this simulation.
     */
    public SimulationNode[] getNodes() {
        return this.nodes;
    }

}
