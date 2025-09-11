package com.hackhud.taskmanager.command.implementation;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.repository.TaskRepository;

public class RemoveTaskCommand implements Command {
    private final TaskRepository repository;
    private final int id;

    public RemoveTaskCommand(TaskRepository repository, int id) {
        this.repository = repository;
        this.id = id;
    }

    @Override
    public void execute() {
        if (repository.remove(id)) {
            System.out.println("Task " + id + " removed.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
}