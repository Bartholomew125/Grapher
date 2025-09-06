import java.awt.Graphics;

/**
 * The interface ScreenObject is used for representing any kind of object that
 * can be placed on the screen.
 */
public interface ScreenObject {
    public void render(Graphics g);
    public void isUnder(int x, int y);
}

