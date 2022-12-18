package maze;

import java.util.Arrays;
import java.util.Scanner;

public class CommandView {
    private final Scanner scanner = new Scanner(System.in);

    public int[] getMazeSize(){
        System.out.println("Please, enter the size of a maze");
        String format = "^\\d* \\d*$";
        String line = scanner.nextLine();
        while(!line.matches(format)){
            line = scanner.nextLine();
        }
        return Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
