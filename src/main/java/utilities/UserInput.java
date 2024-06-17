package utilities;

import java.util.Scanner;

public class UserInput {
    private static  Scanner scanner;

    public static String getUserInput(){
        scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        scanner.close();
        return userInput;
    }

}
