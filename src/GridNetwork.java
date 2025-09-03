/**
 * The class GridNetwork is a network which represents a square lattice.
 */
public class GridNetwork extends Network {
    
    public GridNetwork(int width, int height) {
        super();

        Node[][] nodes = new Node[height][width];
        
        // Create nodes
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes[y][x] = new Node(new TestContent());
            }
        }

        // Connect nodes
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 0) {
                    nodes[y][x].addChild(nodes[y][x-1]);
                }
                if (x < width-1) {
                    nodes[y][x].addChild(nodes[y][x+1]);
                }
                if (y > 0) {
                    nodes[y][x].addChild(nodes[y-1][x]);
                }
                if (y < height-1) {
                    nodes[y][x].addChild(nodes[y+1][x]);
                }
            }
        }

        // Add nodes
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.addNode(nodes[y][x]);
            }
        }

    }
}
