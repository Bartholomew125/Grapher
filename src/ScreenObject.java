import java.awt.Graphics;

/**
 * The interface ScreenObject is used for representing any kind of object that
 * can be placed on the screen.
 */
public interface ScreenObject {
    /**
     * Draws the current ScreenObject on the given Graphics g.
     */
    public void render(Graphics g);

    /**
     * Checks if the ScreenObject is under the given position.
     */
    public boolean isUnder(int x, int y);

    /**
     * Returns whether this ScreenObject is in focus.
     */
    public boolean isFocused();

    /**
     * Sets the focus of this ScreenObject.
     */
    public void isFocused(boolean isFocused);
}

