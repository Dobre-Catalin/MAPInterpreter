package org.example.interpretergui.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import  org.example.interpretergui.View.Command;
import  org.example.interpretergui.View.RunExample;
import  org.example.interpretergui.View.ExitCommand;

public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.values()) {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                printMenu();
                System.out.printf("Input the option: ");

                String key = scanner.nextLine();
                Command com = commands.get(key);
                if (com == null) {
                    System.out.println("Invalid Option");
                    continue;
                }
                com.execute();
            }
        }
        
    }
}