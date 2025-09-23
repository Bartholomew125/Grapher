import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The class InputField represents an input field that can be rendered.
 */
public class InputField extends AbstractScreenObject {
    
    private String text;
    private int textWidth;

    public InputField(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = "";
        this.textWidth = 0;
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("comicsans", Font.BOLD, this.getHeight()));
        int textAscent = g.getFontMetrics().getAscent();
        int textDescent = g.getFontMetrics().getDescent();
        int textHeight = textAscent + textDescent;
        this.textWidth = g.getFontMetrics().stringWidth(this.text);

        if (this.textWidth > this.getWidth()) { this.deleteCharacter(); }

        // Draw background
        g.setColor(Color.GRAY);
        g.fillRect(this.getY(), this.getY(), this.getWidth(), textHeight);

        // Draw text
        g.setColor(Color.PINK);
        g.drawString(this.text, this.getX(), this.getX() + textAscent);

        // If focused, display cursor
        if (this.isFocused()) {
            if ( (int) (2*System.nanoTime() / Math.pow(10, 9)) % 2 == 0){
                g.setColor(Color.LIGHT_GRAY);
            }
            else {
                g.setColor(Color.GRAY);
            }
            g.fillRect(this.getX()+this.textWidth, this.getY(), this.getHeight()/2, this.getHeight());
        }
    }

    public void addCharacter(char c) {
        if (this.textWidth >= this.getWidth()) { return; }
        if ((int) c == 65535 || (int) c == 27) { return; }
        this.text = this.text + c;
    }

    public void deleteCharacter() {
        if (this.text.length() > 0) {
            this.text = this.text.substring(0, this.text.length()-1);
        }
    }

    public String getText() {
        return this.text;
    }

}
