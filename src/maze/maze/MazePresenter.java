package maze.maze;

public class MazePresenter {
    private final String WALL = "\u2588\u2588";
    private final String PASS = "  ";

    public void printMaze(int[][] maze) {
        for (int[] ints : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(ints[j] == 1 ? PASS : WALL);
            }
            System.out.println();
        }
    }
}
