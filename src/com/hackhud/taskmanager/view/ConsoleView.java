package com.hackhud.taskmanager.view;

import com.hackhud.taskmanager.entity.Task;

import java.util.List;

public class ConsoleView {

    public void displayTasks(List<Task> tasks, String header) {
        System.out.println("\n" + header);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        tasks.forEach(task -> System.out.println(task.toString()));
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println("Error: " + error);
    }

    public void displayHelp() {
        System.out.println("\nTASK MANAGEMENT COMMANDS:");
        System.out.println("  add <description>        - Create a new task");
        System.out.println("  update <id> <new desc>   - Modify description of a task");
        System.out.println("  remove <id>              - Remove a task");
        System.out.println("  mark-in-progress <id>    - Set task status to IN_PROGRESS");
        System.out.println("  mark-done <id>           - Set task status to DONE");
        System.out.println("\nVIEWING COMMANDS:");
        System.out.println("  list [status]            - Display tasks (all, or by status: todo, in_progress, done)");
        System.out.println("  help                     - Show this help message");
        System.out.println("  exit                     - Exit the application");
    }
}