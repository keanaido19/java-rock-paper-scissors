package za.co.wethinkcode.rockpaperscissors.ui;

import java.util.Scanner;

public class UserInputPrompt {
    private final String displayText;
    private final Scanner scanner;
    private String value;

    public UserInputPrompt(String displayText) {
        this.displayText = displayText;
        scanner = new Scanner(System.in);
    }

    public void doPrompt() {
        System.out.println(displayText);
        value = scanner.nextLine();
    }

    public String value() {
        return value;
    }
}
