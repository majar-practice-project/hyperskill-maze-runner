package maze.maze;

import java.util.*;

import static maze.maze.MazeElement.*;

public class MazeSolver {
    private int[][] maze;
    private int height;
    private int width;
    private boolean[][] visited;

    public int[][] solve(int[][] maze){
        this.maze = maze.clone();
        height = maze.length;
        width = maze[0].length;
        visited = new boolean[height][width];

        List<Position> exits = findExits();
        if(exits.size() != 2) throw new RuntimeException("Invalid maze");

        Deque<Position> path = findPath(exits.get(0), exits.get(1));
        path.forEach(pos -> this.maze[pos.getX()][pos.getY()] = PATH);    //print path

        return this.maze;
    }

    private Deque<Position> findPath(Position src, Position dest){
        Deque<Position> path = new ArrayDeque<>();
        path.add(src);
        visited[src.getX()][src.getY()] = true;
        path.add(dest);

        visit(path, getNextNodeFromSrc(src), dest);
        return path;
    }

    private Position getNextNodeFromSrc(Position src){
        int x = src.getX();
        int y = src.getY();

        if(x == 0) return new Position(1, y);
        if(x == height-1) return new Position(height-2, y);
        if(y == 0) return new Position(x, 1);
        if(y == width-1) return new Position(x, width-2);

        throw new RuntimeException("Invalid exit");
    }

    private boolean visit(Deque<Position> path, Position node, Position goal){
        int x = node.getX();
        int y = node.getY();
        if(maze[x][y] == WALL || visited[x][y]) return false;
        visited[x][y] = true;
        if(node.equals(goal)) return true;

        boolean isCritical = getNeighbors(node).stream()
                .anyMatch(pos -> visit(path, pos, goal));

        if(isCritical) path.add(node);

        return isCritical;
    }

    private List<Position> getNeighbors(Position node) {
        int x = node.getX();
        int y = node.getY();
        return List.of(
                new Position(x, y - 1),
                new Position(x, y + 1),
                new Position(x - 1, y),
                new Position(x + 1, y)
        );
    }

    private List<Position> findExits(){
        List<Position> exits = new ArrayList<>();

        for(int i=0; i<width; i++){
            if(maze[0][i] == PASS) exits.add(new Position(0,i));
            if(maze[height-1][i] == PASS) exits.add(new Position(height-1,i));
        }

        for(int i=1; i<height-1; i++){
            if(maze[i][0] == PASS) exits.add(new Position(i,0));
            if(maze[i][width-1] == PASS) exits.add(new Position(i, width-1));
        }
        return exits;
    }
}
