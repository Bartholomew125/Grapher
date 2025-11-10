package grapher;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.function.Consumer;

/**
 * The class InputField represents an input field that can be rendered.
 */
public class InputField extends AbstractScreenObject implements MethodCaller<Double> {
    
    private String text;
    private int textWidth;
    private Consumer<Double> method;

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
        g.fillRect(this.getX(), this.getY(), this.getWidth(), textHeight);

        // Draw text
        g.setColor(Color.PINK);
        g.drawString(this.text, this.getX(), this.getY() + textAscent);

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

    public void acceptInput(int keyCode) {
        switch (keyCode) {
            case 8  -> this.deleteCharacter();
            case 10 -> this.call();
            case 27 -> this.isFocused(false);
            default -> this.addCharacter((char) keyCode);
        }
    }

    /**
     * Add a character to the input field.
     */
    public void addCharacter(char c) {
        if (this.textWidth >= this.getWidth()) { return; }
        this.text = this.text + c;
    }

    /**
     * Delete the last character of the input field.
     */
    public void deleteCharacter() {
        if (this.text.length() > 0) {
            this.text = this.text.substring(0, this.text.length()-1);
        }
    }

    /**
     * Get the text of the inputfield.
     */
    public String getText() {
        return this.text;
    }

    @Override
    public void setMethod(Consumer<Double> method) {
        this.method = method;
    }

    @Override
    public void call() {
        if (this.method != null) {
            try {
                this.method.accept(Double.valueOf(this.getText()));
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }

}
