import java.security.DigestOutputStream;
import java.util.List;
import java.util.function.Function;

/**
 * The class GraphSimulator is used to simulate how all the SimulationNodes are
 * suppose to move and interact.
 */
public class GraphSimulator {

    private SimulationNode[] simNodes;
    private SimulationSpring[] springs;

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

        this.springs = new SimulationSpring[network.totalConnections()];
        int i = 0;

        // Map the parents and children of the Nodes in the SimulationNodes, to
        // the parents and children of the SimulationNodes. While doing this,
        // also add a spring between these connections.
        for (SimulationNode simNode : this.simNodes) {
            Node node = simNode.getNode();

            // Check the children
            List<Node> children = node.getChildren();
            for (Node child : children) {
                for (SimulationNode otherSimNode : this.simNodes) {
                    if (otherSimNode.getNode().equals(child)) {
                        simNode.addChild(otherSimNode);
                        this.springs[i] = new SimulationSpring(0.05, 0.01, simNode, otherSimNode);
                        i++;
                    }
                }
            }

            // Check the parents
            List<Node> parents = node.getParents();
            for (Node parent : parents) {
                for (SimulationNode otherSimNode : this.simNodes) {
                    if (otherSimNode.getNode().equals(parent)) {
                        simNode.addParent(otherSimNode);
                        this.springs[i] = new SimulationSpring(0.05, 0.01, simNode, otherSimNode);
                        i++;
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

                // Vector from node1 to node 2
                Vector dir = Vector.subtract(node1.getPosition(), node2.getPosition());

                double repelForce =  0.00001 / dir.getLength();
                dir.normalize();
                dir.scale(repelForce);

                node2.applyForce(dir);
                dir.scale(-1);
                node1.applyForce(dir);
            }
        }

        // Go through each spring and apply forces.
        for (SimulationSpring spring : this.springs) {
            SimulationNode simNode1 = spring.getFirstSimulationNode();
            SimulationNode simNode2 = spring.getSecondSimulationNode();
            
            Vector dir = Vector.subtract(simNode1.getPosition(), simNode2.getPosition());
            double actualLength = dir.getLength();
            double difference = actualLength - spring.desiredLength();
            double force = spring.calculateForce(difference);
            dir.normalize();
            dir.scale(force/2.0);
            simNode1.applyForce(dir);
            dir.scale(-1);
            simNode2.applyForce(dir);
        }

        // Center of simulation.
        Vector center = new Vector(0.5, 0.5);

        // Go through each node once.
        for (SimulationNode node : this.simNodes) {
            Vector nodePosition = new Vector(node.getX(), node.getY());

            // Force towards center.
            Vector centerForce = Vector.subtract(nodePosition, center);
            centerForce.normalize();
            centerForce.scale(0.0005);
            node.applyForce(centerForce);

            // Friction here is the percentage of velocity kept
            double velocity = node.getVelocity().getLength();
            double friction = velocity < 0.00001 ? 0.01 : 0.90;
            node.setDx(node.getDx()*friction);
            node.setDy(node.getDy()*friction);
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
