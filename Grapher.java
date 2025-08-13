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

        for (int i = 0; i < this.graphSimulator.getSimNodes().length; i++) {
            SimulationNode simNode = this.graphSimulator.getSimNodes()[i];
            int x = (int) (simNode.getX() * this.width);
            int y = (int) (simNode.getY() * this.height);

            g.setColor(new Color(simNode.getColorRed(), simNode.getColorGreen(), simNode.getColorBlue())); 
            g.fillOval(x, y, 20, 20);


            for (SimulationNode simChild : simNode.getChildren()) {
                int xChild = (int) (simChild.getX() * this.width);
                int yChild = (int) (simChild.getY() * this.height);

                g.setColor(Color.RED);
                g.drawLine(x, y, xChild, yChild);
            }

        }

    }
}
