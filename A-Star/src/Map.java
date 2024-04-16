import java.util.ArrayList;
import java.util.Random;

public class Map {
    public static  String UNPATHABLE = "x";
    public static  String PATHABLE = "-";

    private final int mSize;
    private final String[][] map;
    private final Node[][] nodes;
    private final String[][] mPath;
    private ArrayList<Node> path;

    public Map() {
        this(15);
    }

    public Map(int size) {
        this.mSize = size;
        this.map = new String[mSize][mSize];
        this.nodes = new Node[mSize][mSize];
        this.mPath = new String[mSize][mSize];
        this.generateMap();
        this.generateNodes();
    }

    public Map(String[][] mapArray) {
        this.mSize = mapArray.length;
        this.map = mapArray;
        this.nodes = new Node[mSize][mSize];
        this.mPath = new String[mSize][mSize];
        this.generateNodes();
    }

    public Map(String[][] mapArray, String pathSymbol, String noPathSymbol) {
        this(mapArray);
        UNPATHABLE = noPathSymbol;
        PATHABLE = pathSymbol;
    }

    private void generateMap() {
        Random random = new Random();
        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < mSize; j++) {
                map[i][j] = random.nextDouble() < 0.1 ? UNPATHABLE : PATHABLE;
            }
        }
    }

    private void generateNodes() {
        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < mSize; j++) {
                nodes[i][j] = new Node(i, j, map[i][j].equals(PATHABLE) ? Node.PATHABLE : Node.UNPATHABLE);
            }
        }
    }

    public void generatePath(int startRow, int startCol, int goalRow, int goalCol) {
        AStar aStar = new AStar(nodes, startRow, startCol, goalRow, goalCol, mSize);
        path = aStar.isPathFound() ? aStar.getPath() : null;
    }

    public String displayPath() {
        return path != null ? pathToString() : "No path could not be found";
    }

    public void updateMap() {
        resetPathedMap();
        if (path != null) {
            int counter = 1;
            for (Node node : path) {
                mPath[node.getRow()][node.getCol()] = String.valueOf(counter++);
            }
        }
    }

    private void resetPathedMap() {
        for (int i = 0; i < mSize; i++) {
            System.arraycopy(map[i], 0, mPath[i], 0, mSize);
        }
    }

    public void resetNodes() {
        generateNodes();
    }

    public void resetPath() {
        path.clear();
    }

    public String[][] getMap() {
        return map;
    }

    public String[][] getPathedMap() {
        return mPath;
    }

    public int getMapSize() {
        return mSize;
    }

    public String getType(int row, int col) {
        return map[row][col];
    }

    public void setElement(int row, int column, String symbol) {
        map[row][column] = symbol;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\t");
        for (int i = 0; i < mSize; i++) {
            result.append(i).append("\t");
        }
        result.append("\n");

        for (int i = 0; i < mSize; i++) {
            result.append(i).append("\t");
            for (int j = 0; j < mSize; j++) {
                result.append(map[i][j]).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public String pathToString() {
        StringBuilder result = new StringBuilder("\t");
        for (int i = 0; i < mSize; i++) {
            result.append(i).append("\t");
        }
        result.append("\n");

        for (int i = 0; i < mSize; i++) {
            result.append(i).append("\t");
            for (int j = 0; j < mSize; j++) {
                result.append(mPath[i][j]).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
