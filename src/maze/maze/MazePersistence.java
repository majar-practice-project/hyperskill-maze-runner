package maze.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MazePersistence {
    public void saveMaze(int[][] maze, File file) throws FileNotFoundException {
        try(PrintWriter pw = new PrintWriter(file)){
            int height = maze.length;
            int width = maze[0].length;
            pw.printf("%d %d%n", height, width);
            for(int[] row: maze){
                pw.println(Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")));
            }
        }
    }

    public int[][] loadMaze(File file) throws FileNotFoundException{
        try(Scanner scanner = new Scanner(file)){
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            int[][] maze = new int[height][width];
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){
                    maze[i][j] = scanner.nextInt();
                }
            }
            return maze;
        }
    }
}