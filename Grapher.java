import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * The class Grapher extends JPanel, and is used to draw a GraphSimulation on
 * the display.
 */
public class Grapher extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {

    private GraphSimulator graphSimulator;
    private double scale;
    private double xOffset;
    private double yOffset;
    private Vector mousePos;
    private SimulationNode draggingNode;

    public Grapher(GraphSimulator graphSimulator, int width, int height) {
        this.graphSimulator = graphSimulator;
        this.scale = Math.min(width, height);
        this.draggingNode = null;
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.xOffset = 0.0;
        this.yOffset = 0.0;
        this.mousePos = new Vector(0, 0);
        this.setBackground(Color.DARK_GRAY);
    }

    protected void drawLineWithWidth(Graphics g, int x1, int y1, int x2, int y2, int width) {
        Vector p1 = new Vector(x1, y1);
        Vector p2 = new Vector(x2, y2);
        Vector dir = Vector.subtract(p1, p2);

        // Point 1 & 2
        dir.rotate(Math.PI / 2);
        dir.normalize();
        dir.scale(width / 2);
        p1 = Vector.add(p1, dir);
        p2 = Vector.add(p2, dir);

        dir.rotate(Math.PI);
        dir.normalize();

        Vector[] points1 = new Vector[width];
        Vector[] points2 = new Vector[width];
        points1[0] = p1.copy();
        points2[0] = p2.copy();
        for (int i = 1; i < width; i++) {
            p1 = Vector.add(p1, dir);
            p2 = Vector.add(p2, dir);
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

        // Make sure draggingNode is attached even when not moving mouse.
        if (this.draggingNode != null) {
            this.draggingNode.setPosition(fromScreenSpaceToWorldSpace(this.mousePos));
            this.draggingNode.setDx(0);
            this.draggingNode.setDy(0);
        }

        // Show connecting lines
        for (int i = 0; i < this.graphSimulator.nodeCount(); i++) {
            SimulationNode simNode = this.graphSimulator.getSimNodes()[i];
            int x = (int) (simNode.getX() * this.scale + this.xOffset);
            int y = (int) (simNode.getY() * this.scale + this.yOffset);

            // Show child lines
            g.setColor(Color.GREEN);
            for (SimulationNode simChild : simNode.getChildren()) {
                int xChild = (int) (simChild.getX() * this.scale + this.xOffset);
                int yChild = (int) (simChild.getY() * this.scale + this.yOffset);
                g.drawLine(x, y, xChild, yChild);
            }

            // Show parent lines
            g.setColor(Color.BLUE);
            for (SimulationNode simParent : simNode.getParents()) {
                int xParent = (int) (simParent.getX() * this.scale + this.xOffset);
                int yParent = (int) (simParent.getY() * this.scale + this.yOffset);
                g.drawLine(x, y, xParent, yParent);
            }
        }

        // Show nodes
        for (int i = 0; i < this.graphSimulator.nodeCount(); i++) {
            SimulationNode simNode = this.graphSimulator.getSimNodes()[i];
            int x = (int) (simNode.getX() * this.scale + this.xOffset);
            int y = (int) (simNode.getY() * this.scale + this.yOffset);

            int radius = (int) (simNode.getRadius() * this.scale);
            g.setColor(simNode.getColor());
            g.fillOval(x - radius, y - radius, radius*2, radius*2);
        }

        // Show content when mouse over
        for (SimulationNode node : this.graphSimulator.getSimNodes()) {
            if (this.fromScreenSpaceToWorldSpace(this.mousePos).distanceTo(node.getPosition()) <= node.getRadius()) {
                this.showContentPopup(g, node);
            }
            
        }
    }

    /**
     * Draws a box containing the contents of the node.
     */
    private void showContentPopup(Graphics g, SimulationNode node) {
        Vector screenPos = fromWorldSpaceToScreenSpace(node.getPosition());
        double radius = node.getRadius() * this.scale;
        String content = node.getNode().getContent().toString();
        Vector pos = Vector.add(screenPos, new Vector(radius, -radius));

        g.setFont(new Font("ComicSans", Font.BOLD, 20));
        
        int x = (int) pos.getX();
        int y = (int) pos.getY();
        int width = g.getFontMetrics().stringWidth(content);
        int ascent = g.getFontMetrics().getAscent();
        int decent = g.getFontMetrics().getDescent();
        int height = ascent + decent;

        g.setColor(new Color(255, 165, 0));
        g.fillRect(x, y-ascent, width, height);
        g.setColor(Color.DARK_GRAY);
        g.drawString(content, x, y);
    }

    /**
     * Converts a screen coordinate to a world coordinate in graph simulation
     * space.
     */
    private Vector fromScreenSpaceToWorldSpace(Vector screenPos) {
        Vector worldPos = screenPos.copy();
        worldPos.setX(worldPos.getX() - this.xOffset);
        worldPos.setY(worldPos.getY() - this.yOffset);
        worldPos.scale(1.0/this.scale);
        return worldPos;
    }

    /**
     * Converts a screen coordinate to a world coordinate in graph simulation
     * space.
     */
    private Vector fromWorldSpaceToScreenSpace(Vector worldPos) {
        Vector screenPos = worldPos.copy();
        screenPos.scale(this.scale);
        screenPos.setX(screenPos.getX() + this.xOffset);
        screenPos.setY(screenPos.getY() + this.yOffset);
        return screenPos;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double wheelRotation = e.getPreciseWheelRotation();
        double scaleMultiplier = (wheelRotation > 0)
                ? Math.pow(0.9, wheelRotation) // zoom out
                : Math.pow(1.1, -wheelRotation); // zoom in

        // World coords of cursor before zoom
        double worldX = (e.getX() - xOffset) / scale;
        double worldY = (e.getY() - yOffset) / scale;

        // Apply zoom
        scale *= scaleMultiplier;

        // Recalculate offsets to keep cursor anchored
        xOffset = e.getX() - worldX * scale;
        yOffset = e.getY() - worldY * scale;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Vector newPos = new Vector(e.getX(), e.getY());
        if (this.draggingNode != null) {
            this.draggingNode.setPosition(fromScreenSpaceToWorldSpace(newPos));
            this.mousePos = new Vector(e.getX(), e.getY());
        } else {
            Vector deltaPos = Vector.subtract(this.mousePos, newPos);
            this.xOffset += deltaPos.getX();
            this.yOffset += deltaPos.getY();
            this.mousePos = newPos;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mousePos = new Vector(e.getX(), e.getY());
        Vector worldPos = fromScreenSpaceToWorldSpace(this.mousePos);
        this.draggingNode = this.graphSimulator.nodeAtPosition(worldPos.getX(), worldPos.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.draggingNode = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePos = new Vector(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
