import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The class Grapher extends JPanel, and is used to draw a GraphSimulation on
 * the display.
 */
public class Grapher extends JPanel {

    private GraphSimulator graphSimulator;
    private int width;
    private int height;

    public Grapher(GraphSimulator graphSimulator, int width, int height){
        this.width = width;
        this.height = height;
        this.graphSimulator = graphSimulator;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < this.graphSimulator.getNodes().length; i++) {
            SimulationNode node = this.graphSimulator.getNodes()[i];
            int x = (int) (node.getX() * this.width);
            int y = (int) (node.getY() * this.height);

            g.setColor(new Color(node.getColorRed(), node.getColorGreen(), node.getColorBlue())); 
            g.fillOval(x, y, 20, 20);
        }

    }
}
