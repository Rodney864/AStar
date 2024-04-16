import java.util.ArrayList;

public class AStar {
    private Node[][] nodes;
    private Node start;
    private Node goal;
    private int boundary;
    private boolean pathFound = false;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private ArrayList<Node> path;

    public AStar(Node[][] nodes, int startRow, int startCol, int goalRow, int goalCol, int boundary) {
        this.boundary = boundary;
        this.nodes = nodes;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        goal = nodes[goalRow][goalCol];
        start = nodes[startRow][startCol];
        start.setG(0);
        start.setH(calculateHeuristic(start));
        start.setF();
        start.setParent(null);
        openList.add(start);
        search();
    }

    private void search() {
        while (!openList.isEmpty()) {
            Node current = findLowest();
            openList.remove(current);

            if (current.equals(goal)) {
                pathFound = true;
                generatePath();
                break;
            }

            generateNeighbors(current);
            closedList.add(current);
        }
    }

    private void generateNeighbors(Node node) {
        int row = node.getRow();
        int col = node.getCol();
        int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isValidPosition(newRow, newCol)) {
                Node neighbor = nodes[newRow][newCol];
                int moveCost = node.getG() + (i < 4 ? 10 : 14);

                if (neighbor.getG() == 0 || moveCost < neighbor.getG()) {
                    neighbor.setG(moveCost);
                    neighbor.setH(calculateHeuristic(neighbor));
                    neighbor.setF();
                    neighbor.setParent(node);

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < boundary && col >= 0 && col < boundary
                && nodes[row][col].getType() == Node.PATHABLE
                && !closedList.contains(nodes[row][col]);
    }

    private void generatePath() {
        path = new ArrayList<>();
        Node current = goal;

        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public boolean isPathFound() {
        return pathFound;
    }

    private Node findLowest() {
        Node lowest = openList.get(0);

        for (Node node : openList) {
            if (node.getF() < lowest.getF()) {
                lowest = node;
            }
        }

        return lowest;
    }

    private int calculateHeuristic(Node node) {
        return Math.abs(node.getRow() - goal.getRow()) * 10
                + Math.abs(node.getCol() - goal.getCol()) * 10;
    }
}
