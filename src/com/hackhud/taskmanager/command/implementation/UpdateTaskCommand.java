package com.hackhud.taskmanager.command.implementation;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.entity.Task;
import com.hackhud.taskmanager.repository.TaskRepository;

import java.util.Optional;

public class UpdateTaskCommand implements Command {
    private final TaskRepository repository;
    private final int id;
    private final String newTitle;

    public UpdateTaskCommand(TaskRepository repository, int id, String newTitle) {
        this.repository = repository;
        this.id = id;
        this.newTitle = newTitle;
    }

    @Override
    public void execute() {
        Optional<Task> taskOptional = repository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.updateTitle(newTitle);
            System.out.println("Task " + id + " updated.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
}