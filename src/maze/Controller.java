package maze;

import maze.maze.MazeGenerator;
import maze.maze.MazePersistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class Controller {
    private final CommandView view = new CommandView();
    private final MazePersistence storage = new MazePersistence();
    private int[][] maze;
    private boolean mazePresent;

    public void start() {
        while (true) {
            if (!mazePresent) {
                processCommand();
            } else {
                processAdvancedCommand();
            }
        }
    }

    public void processCommand() {
        int input = view.showStarterMenu();
        switch (input) {
            case 0:
                view.showBye();
                System.exit(0);
            case 1:
                generateMaze();
                break;
            case 2:
                loadMaze();
                break;
        }
    }

    public void processAdvancedCommand() {
        int input = view.showMazePresentMenu();
        switch (input) {
            case 0:
                view.showBye();
                System.exit(0);
            case 1:
                generateMaze();
                break;
            case 2:
                loadMaze();
                break;
            case 3:
                saveMaze();
                break;
            case 4:
                view.showMaze(maze);
        }
    }

    public void generateMaze() {
        int size = view.getSquareMazeSize();
        int[][] maze = new MazeGenerator(size, size).generate();
        view.showMaze(maze);
        this.maze = maze;
        mazePresent = true;
    }

    public void saveMaze() {
        String fileName = view.getFileName();
        try {
            storage.saveMaze(maze, new File(fileName));
        } catch (FileNotFoundException e) {
            view.showFileNotExist(fileName);
        }
    }

    public void loadMaze() {
        String fileName = view.getFileName();
        try {
            maze = storage.loadMaze(new File(fileName));
            mazePresent = true;
        } catch (FileNotFoundException e) {
            view.showFileNotExist(fileName);
        } catch (NoSuchElementException e) {
            view.showInvalidFormat();
        }
    }
}
