package maze;

import maze.maze.MazeGenerator;
import maze.maze.MazePresenter;

public class Controller {
    private final CommandView view = new CommandView();

    public void start(){
        int[] sizes = view.getMazeSize();
        int[][] maze = new MazeGenerator(sizes[0],sizes[1]).generate();
        new MazePresenter().printMaze(maze);
    }
}
