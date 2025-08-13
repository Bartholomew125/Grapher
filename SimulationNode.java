import java.util.List;

/**
 * The class SimulationNode is a shell to put around a node, so it can be used
 * in a simulation, by giving it a position, velocity and color. This class
 * comes with methods to update the position, get the position and color, and
 * apply a force to the node.
 */
public class SimulationNode {

    private final Node<?> node;
    private List<SimulationNode> parents;
    private List<SimulationNode> children;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private float red;
    private float green;
    private float blue;

    public SimulationNode(Node<?> node, double x, double y, float red, float green, float blue) {
        this.node = node;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Adds the velocity of the SimulationNode to its position.
     */
    public void updatePosition() {
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
    }

    /**
     * Get the horizontal position of the SimulationNode.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the vertical position of the SimulationNode.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Return the node associated with this SimulationNode.
     */
    public Node<?> getNode() {
        return this.node;
    }

    /**
     * Get the red color value of the SimulationNode.
     */
    public float getColorRed() {
        return this.red;
    }

    /**
     * Get the green color value of the SimulationNode.
     */
    public float getColorGreen() {
        return this.green;
    }

    /**
     * Get the blue color value of the SimulationNode.
     */
    public float getColorBlue() {
        return this.blue;
    }

    /**
     * Applies the given force vector, by adding it to
     * the velocity of the SimulationNode.
     */
    public void applyForce(Vector force) {
        this.dx = this.dx + force.getX();
        this.dy = this.dy + force.getY();
    }
}
