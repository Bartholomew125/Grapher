import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;


/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        int width = 1000;
        int height = 1000;
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        Network network = new Network();
        for (int i = 0; i < 2; i++) {
            network.addNode(new Node(new NodeContentTest()));
        }
        for (int i = 0; i < network.size()-1; i++) {
            for (int j = i+1; j < network.size(); j++) {
                Node node1 = network.getNodes().get(i);
                Node node2 = network.getNodes().get(j);

                node1.addParent(node2);
                node2.addChild(node1);
            }
        }

        GraphSimulator graphsim = new GraphSimulator(network);
        Grapher graph = new Grapher(graphsim, width, height);

        JFrame frame = new JFrame("2D Graphics Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.add(graph);
        frame.setVisible(true);
        frame.setBackground(Color.BLACK);
        frame.setLocation((screenWidth-width)/2, (screenHeight-height)/2);

        double fps = 60.0;
        double nanosecondsPerFrame = 1.0/fps * Math.pow(10, 9);
        long currentTime = System.nanoTime();
        while (true) {
            if (System.nanoTime()-currentTime >= nanosecondsPerFrame) {
                currentTime = System.nanoTime();
                graphsim.update();
                frame.repaint();
            }
        }
    }
}
