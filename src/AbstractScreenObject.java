import java.awt.Graphics;

/**
 * The abstract class AbstractScreenObject is an abstract implementation of the
 * interface ScreenObject. This class enables a position and size of anything
 * that extends it, and requires it to have a render method.
 */
public abstract class AbstractScreenObject implements ScreenObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isFocused;

    public AbstractScreenObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isFocused = false;
    }
    
    @Override
    public void render(Graphics g) {}

    @Override
    public boolean isUnder(int x, int y) {
        return this.getX() <= x && x <= this.getX()+this.width &&
               this.getY() <= y && y <= this.getY()+this.height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isFocused() {
        return this.isFocused;
    }

    public void isFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

}
