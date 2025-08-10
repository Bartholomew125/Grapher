/**
 * The class Vector represents a 2D vector of doubles. It contains methods for
 * accessing and setting its components, and getting the length of it.
 */
public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x component of this vector.
     */
    public double getX() {
        return this.x;
    }


    /**
     * Returns the y component of this vector.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Returns the length of the vector.
     */
    public double getLength() {
        double a = this.getX() * this.getX();
        double b = this.getY() * this.getY();
        return Math.sqrt(a+b);
    }

}
