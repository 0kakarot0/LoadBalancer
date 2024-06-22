package utilities;

import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
    private static  Scanner scanner;

    public static String getUserInput(){
        scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        scanner.close();
        return userInput;
    }

    public static String singleURL(String userInput){
        String[] totalParts = userInput.split(" ");
        return totalParts[1];
    }
}
