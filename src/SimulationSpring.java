/**
 * The class SimulationSpring represents a string in the graph simulation. The
 * spring is defined by its resting length, and a spring constant. This class 
 * also holds the the SimulationNodes that it is connected to.
 */
public class SimulationSpring {

    private double restingLength;
    private double stiffness;
    private final SimulationNode simNode1;
    private final SimulationNode simNode2;

    public SimulationSpring(double restingLength, double k, SimulationNode simNode1, SimulationNode simNode2) {
        this.restingLength = restingLength;
        this.stiffness = k;
        this.simNode1 = simNode1;
        this.simNode2 = simNode2;
    }

    /**
     * Calculates the force to return the spring to its resting position, given
     * by Hooke's law.
     */
    public double calculateForce(double x) {
        return this.stiffness * x;
    }

    /**
     * Returns the first connected SimulationNode.
     */
    public SimulationNode getFirstSimulationNode() {
        return this.simNode1;
    }

    /**
     * Returns the second connected SimulationNode.
     */
    public SimulationNode getSecondSimulationNode() {
        return this.simNode2;
    }

    /**
     * Get the desired length of the spring.
     */
    public double desiredLength() {
        return this.restingLength;
    }

    /**
     * Set the resting length of the spring.
     */
    public void setRestingLength(double restingLength) {
        this.restingLength = restingLength;
    }

    /**
     * Set the spring constant (stiffness) of the spring.
     */
    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }
}
