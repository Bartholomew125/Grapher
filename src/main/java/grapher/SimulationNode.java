package grapher;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class SimulationNode is a shell to put around a node, so it can be used
 * in a simulation, by giving it a position, velocity and color. This class
 * comes with methods to update the position, get the position and color, and
 * apply a force to the node.
 */
public class SimulationNode {

    private final Node node;
    private List<SimulationNode> parents;
    private List<SimulationNode> children;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double mass;
    private double radius;
    private Color color;

    public SimulationNode(Node node, double x, double y, double mass, double radius, Color color) {
        this.node = node;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.mass = mass;
        this.radius = radius;
        this.color = color;
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
     * Set the horizontal position of the SimulationNode.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the vertical position of the SimulationNode.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set the vertical position of the SimulationNode.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Return a new Vector representing the position of this SimulationNode
     */
    public Vector getPosition() {
        return new Vector(this.getX(), this.getY());
    }

    /**
     * Set the position of this SimulationNode using a Vector.
     */
    public void setPosition(Vector pos) {
        this.setX(pos.getX());
        this.setY(pos.getY());
    }

    /**
     * Get the horizontal velocity of the SimulationNode.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get the vertical velocity of the SimulationNode.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Return a new Vector representing the velocity of this SimulationNode
     */
    public Vector getVelocity() {
        return new Vector(this.getDx(), this.getDy());
    }

    /**
     * Set the horizontal velocity of the SimulationNode.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set the vertical velocity of the SimulationNode.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Return the mass of this SimulationNode.
     */
    public double getMass() {
        return this.mass;
    }

    /**
     * Return the radius of this SimulationNode.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of this SimulationNode.
     */
    public void setRadius(double r) {
        this.radius = r;
    }

    /**
     * Return the node associated with this SimulationNode.
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Get the color value of the SimulationNode.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Applies the given force vector, by adding it to
     * the velocity of the SimulationNode.
     */
    public void applyForce(Vector force) {
        this.dx = this.dx + force.getX() / this.getMass();
        this.dy = this.dy + force.getY() / this.getMass();
    }

    /**
     * Add a SimulationNode as a parent to this SimulationNode.
     */
    public void addParent(SimulationNode simNode) {
        this.parents.add(simNode);
    }

    /**
     * Add a SimulationNode as a child to this SimulationNode.
     */
    public void addChild(SimulationNode simNode) {
        this.children.add(simNode);
    }

    /**
     * Return the SimulatioNode parents of this SimulationNode.
     */
    public List<SimulationNode> getParents() {
        return this.parents;
    }

    /**
     * Return the SimulatioNode children of this SimulationNode.
     */
    public List<SimulationNode> getChildren() {
        return this.children;
    }

    /**
     * Check whether simNode is a parent of this SimulationNode.
     */
    public boolean hasParent(SimulationNode simNode) {
        for (SimulationNode parent : this.getParents()) {
            if (parent.equals(simNode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether simNode is a child of this SimulatioNode.
     */
    public boolean hasChild(SimulationNode simNode) {
        for (SimulationNode parent : this.getParents()) {
            if (parent.equals(simNode)) {
                return true;
            }
        }
        return false;
    }
}
