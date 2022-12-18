package maze.maze;

import maze.util.RandomizedSet;

import java.util.*;

public class MazeGenerator {
    private final int height;
    private final int width;
    private int[][] maze;

    private static final int WALL = 0;
    private static final int PASS = 1;

    public MazeGenerator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int[][] generate() {
        maze = new int[height][width];
        for (int i = 1; i < height; i += 2) {
            for (int j = 1; j < width; j += 2) {
                maze[i][j] = PASS;
            }
        }

        int totalNodes = (height >> 1) * (width >> 1);

        Set<Position> visitedNodes = new HashSet<>();
        RandomizedSet<Position> pendingNodes = new RandomizedSet<>();
        addConnectedNode(new Position(1, 1), visitedNodes, pendingNodes);

        while (visitedNodes.size() < totalNodes) {
            Position randomNode = pendingNodes.popRandom();
            addRandomEdge(randomNode, visitedNodes);
            addConnectedNode(randomNode, visitedNodes, pendingNodes);
        }

        fillEdges();
        addExits();

        return maze;
    }

    private void addConnectedNode(Position node, Set<Position> visitedNodes, RandomizedSet<Position> pendingNodes) {
        visitedNodes.add(node);
        getNeighbors(node).stream()
                .filter(neighbor -> !visitedNodes.contains(neighbor) && isValidNode(neighbor))
                .forEach(pendingNodes::add);
    }

    private void addRandomEdge(Position dest, Set<Position> visitedNodes) {
        List<Position> neighbors = new ArrayList<>(getNeighbors(dest));
        Collections.shuffle(neighbors);
        Position source = neighbors.stream()
                .filter(visitedNodes::contains)
                .findFirst().orElseThrow();
        maze[(source.getX() + dest.getX()) / 2][(source.getY() + dest.getY()) / 2] = PASS;
    }

    private List<Position> getNeighbors(Position node) {
        int x = node.getX();
        int y = node.getY();
        return List.of(
                new Position(x, y - 2),
                new Position(x, y + 2),
                new Position(x - 2, y),
                new Position(x + 2, y)
        );
    }

    private boolean isValidNode(Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        if (x < 1 || x >= height || y < 1 || y >= width) return false;
        return true;
    }

    private void fillEdges() {
        if (height % 2 == 0) {
            maze[height - 2] = maze[height - 1];
        }
        maze[height - 1] = new int[width];

        if (width % 2 == 0) {
            for (int i = 0; i < height; i++) {
                maze[i][width - 2] = maze[i][width - 1];
            }
        }
        for (int i = 0; i < height; i++) {
            maze[i][width - 1] = WALL;
        }
    }

    private void addExits() {
        maze[0][1] = PASS;
        for (int i = height - 1; i >= 0; i--) {
            if (maze[i][width - 2] == 1) {
                maze[i][width - 1] = PASS;
                return;
            }
        }
    }
}
