package com.hackhud.taskmanager;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.command.CommandExecutor;
import com.hackhud.taskmanager.exception.InvalidCommandException;
import com.hackhud.taskmanager.parser.CommandParser;
import com.hackhud.taskmanager.repository.TaskRepository;
import com.hackhud.taskmanager.repository.impl.JsonTaskRepository;
import com.hackhud.taskmanager.view.ConsoleView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskRepository repository = new JsonTaskRepository("tasks.json");
        ConsoleView view = new ConsoleView();
        CommandParser parser = new CommandParser(repository, view);
        CommandExecutor executor = new CommandExecutor();
        Scanner scanner = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(repository::save));

        view.displayMessage("Task Manager. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                Command command = parser.parse(input);
                executor.setCommand(command);
                executor.run();
            } catch (InvalidCommandException e) {
                view.displayError(e.getMessage());
            } catch (Exception e) {
                view.displayError("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}