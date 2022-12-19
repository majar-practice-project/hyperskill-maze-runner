package maze.maze;

import java.util.Map;

import static maze.maze.MazeElement.*;

public class MazePresenter {
    private final Map<Integer, String> presentMap = Map.of(
            WALL, "\u2588\u2588",
            PASS, "  ",
            PATH, "//"
    );

    public void printMaze(int[][] maze) {
        for (int[] ints : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(presentMap.get(ints[j]));
            }
            System.out.println();
        }
    }
}
