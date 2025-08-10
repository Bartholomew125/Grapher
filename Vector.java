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
     * Returns a new vector which is this vector minus the given vector v.
     */
    public Vector subtract(Vector v) {
        double x = this.getX() - v.getX();
        double y = this.getY() - v.getY();
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

}
