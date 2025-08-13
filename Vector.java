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
     * Sets a new x value for this vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets a new y value for this vector.
     */
    public void setY(double y) {
        this.y = y;
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

    /**
     * Subtracts v1 from v2, and return it as a new vector.
     */
    public static Vector subtract(Vector v1, Vector v2) {
        double x = v2.getX() - v1.getX();
        double y = v2.getY() - v1.getY();
        return new Vector(x, y);
    }

    /**
     * Adds the vector v to the this vector.
     */
    public void add(Vector v) {
        this.setX(this.getX() + v.getX());
        this.setY(this.getY() + v.getY());
    }

    /**
     * Add together v1 and v2 and return it as a new Vector.
     */
    public static Vector add(Vector v1, Vector v2) {
        double x = v1.getX() + v2.getX();
        double y = v1.getY() + v2.getY();
        return new Vector(x, y);
    }

    /**
     * Scales the vector to be length 1.
     */
    public void normalize() {
        double length = this.getLength();
        this.setX( this.getX()/length );
        this.setY( this.getY()/length );
    }

    /**
     * Scales the vector by the factor k.
     */
    public void scale(double k) {
        this.setX( this.getX()*k );
        this.setY( this.getY()*k );
    }

    /**
     * Rotate this vector the given amount of radians counterclockwise
     */
    public void rotate(double radians) {
        // Convert to polar
        double length = this.getLength();
        double angle = Math.atan2(this.getY(), this.getX());
        angle = angle + radians;
        // Convert to cartesian
        this.setX(Math.cos(angle) * length);
        this.setY(Math.sin(angle) * length);
    }
}
