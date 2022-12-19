package maze;

import maze.maze.MazePresenter;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandView {

    private final MazePresenter presenter = new MazePresenter();
    private final Scanner scanner = new Scanner(System.in);

    public int showStarterMenu(){
        System.out.println("\n=== Menu ===\n" +
                "1. Generate a new maze\n" +
                "2. Load a maze\n" +
                "0. Exit");
        return getInputFromRange(3);
    }

    public int getInputFromRange(int range){
        Set<String> validInputs = IntStream.range(0, range).mapToObj(String::valueOf).collect(Collectors.toSet());
        String line = scanner.nextLine();
        while(!validInputs.contains(line)){
            System.out.println("Incorrect option. Please try again");
            line = scanner.nextLine();
        }
        return Integer.parseInt(line);
    }

    public int showMazePresentMenu(){
        System.out.println("\n=== Menu ===\n" +
                "1. Generate a new maze\n" +
                "2. Load a maze\n" +
                "3. Save the maze\n" +
                "4. Display the maze\n" +
                "5. Find the escape\n" +
                "0. Exit");
        return getInputFromRange(6);
    }

    public int[] getMazeSize(){
        System.out.println("Please, enter the size of a maze");
        String format = "^\\d* \\d*$";
        String line = scanner.nextLine();
        while(!line.matches(format)){
            line = scanner.nextLine();
        }
        return Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public int getSquareMazeSize(){
        System.out.println("Please, enter the size of a maze");
        String format = "^\\d*$";
        String line = scanner.nextLine();
        while(!line.matches(format)){
            line = scanner.nextLine();
        }
        return Integer.parseInt(line);
    }

    public void showBye(){
        System.out.println("Bye!");
    }

    public void showMaze(int[][] maze){
        presenter.printMaze(maze);
    }

    public String getFileName(){
        return scanner.nextLine();
    }

    public void showFileNotExist(String fileName){
        System.out.println("The file ... does not exist");
    }

    public void showInvalidFormat(){
        System.out.println("Cannot load the maze. It has an invalid format");
    }
}
