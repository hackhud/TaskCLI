package com.hackhud.taskmanager.parser;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.command.implementation.*;
import com.hackhud.taskmanager.entity.enums.TaskStatus;
import com.hackhud.taskmanager.exception.InvalidCommandException;
import com.hackhud.taskmanager.repository.TaskRepository;
import com.hackhud.taskmanager.view.ConsoleView;

import java.util.Arrays;

public class CommandParser {

    private final TaskRepository repository;
    private final ConsoleView view;

    public CommandParser(TaskRepository repository, ConsoleView view) {
        this.repository = repository;
        this.view = view;
    }

    public Command parse(String input) throws InvalidCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("Command cannot be empty.");
        }

        String[] parts = input.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String[] args = (parts.length > 1) ? parts[1].split("\\s+") : new String[0];
        String fullArg = (parts.length > 1) ? parts[1] : "";


        switch (commandName) {
            case "help":
                return new HelpCommand(view);
            case "add":
                if (fullArg.isEmpty()) throw new InvalidCommandException("Usage: add <task title>");
                return new AddTaskCommand(repository, fullArg);
            case "remove":
                return new RemoveTaskCommand(repository, parseId(args));
            case "list":
                TaskStatus status = args.length > 0 ? TaskStatus.fromLabel(args[0].toUpperCase()) : null;
                if (args.length > 0 && status == null) {
                    throw new InvalidCommandException("Unknown status: " + args[0]);
                }
                return new ListTasksCommand(repository, view, status);
            case "update":
                String[] updateArgs = fullArg.split("\\s+", 2);
                if (updateArgs.length < 2) throw new InvalidCommandException("Usage: update <id> <new title>");
                int updateId = parseId(new String[]{updateArgs[0]});
                return new UpdateTaskCommand(repository, updateId, updateArgs[1]);
            case "mark-done":
                return new UpdateStatusCommand(repository, parseId(args), TaskStatus.DONE);
            case "mark-in-progress":
                return new UpdateStatusCommand(repository, parseId(args), TaskStatus.IN_PROGRESS);
            default:
                throw new InvalidCommandException("Unknown command: " + commandName);
        }
    }

    private int parseId(String[] args) throws InvalidCommandException {
        if (args.length != 1) {
            throw new InvalidCommandException("Command requires a single numeric ID.");
        }
        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Task ID must be a number.");
        }
    }
}