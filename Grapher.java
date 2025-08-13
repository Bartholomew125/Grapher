import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

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
    
    protected void drawLineWithWidth(Graphics g, int x1, int y1, int x2, int y2, int width) {
        Vector p1 = new Vector(x1, y1);
        Vector p2 = new Vector(x2, y2);
        Vector dir = Vector.subtract(p1, p2);
        
        // Point 1 & 2
        dir.rotate(Math.PI/2);
        dir.normalize();
        dir.scale(width/2);
        p1 = Vector.add(p1, dir);
        p2 = Vector.add(p2, dir);

        dir.rotate(Math.PI);
        dir.normalize();

        Vector[] points1 = new Vector[width];
        Vector[] points2 = new Vector[width];
        points1[0] = p1.copy();
        points2[0] = p2.copy();
        for (int i = 1; i < width; i++) {
            p1 = Vector.add(p1,dir);
            p2 = Vector.add(p2,dir);
            points1[i] = p1.copy();
            points2[i] = p2.copy();
        }

        for (Vector point1 : points1) {
            for (Vector point2 : points2) {
                g.drawLine((int) point1.getX(), (int) point1.getY(), (int) point2.getX(), (int) point2.getY());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Show connecting lines
        for (int i = 0; i < this.graphSimulator.nodeCount(); i++) {
            SimulationNode simNode = this.graphSimulator.getSimNodes()[i];
            int x = (int) (simNode.getX() * this.width);
            int y = (int) (simNode.getY() * this.height);

            // Show child lines
            g.setColor(Color.RED);
            for (SimulationNode simChild : simNode.getChildren()) {
                int xChild = (int) (simChild.getX() * this.width);
                int yChild = (int) (simChild.getY() * this.height);
                g.drawLine(x, y, xChild, yChild);
            }

            // Show parent lines
            g.setColor(Color.BLUE);
            for (SimulationNode simParent : simNode.getParents()) {
                int xParent = (int) (simParent.getX() * this.width);
                int yParent = (int) (simParent.getY() * this.height);
                g.drawLine(x, y, xParent, yParent);
            }
        }

        // Show nodes
        for (int i = 0; i < this.graphSimulator.nodeCount(); i++) {
            SimulationNode simNode = this.graphSimulator.getSimNodes()[i];
            int x = (int) (simNode.getX() * this.width);
            int y = (int) (simNode.getY() * this.height);

            int radius = 20;
            g.setColor(new Color(simNode.getColorRed(), simNode.getColorGreen(), simNode.getColorBlue())); 
            g.fillOval(x-radius/2, y-radius/2, radius, radius);
        }
    }
}
