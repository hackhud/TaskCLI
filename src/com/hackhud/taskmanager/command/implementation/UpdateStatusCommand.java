package com.hackhud.taskmanager.command.implementation;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.entity.Task;
import com.hackhud.taskmanager.entity.enums.TaskStatus;
import com.hackhud.taskmanager.repository.TaskRepository;

import java.util.Optional;

public class UpdateStatusCommand implements Command {
    private final TaskRepository repository;
    private final int id;
    private final TaskStatus newStatus;

    public UpdateStatusCommand(TaskRepository repository, int id, TaskStatus newStatus) {
        this.repository = repository;
        this.id = id;
        this.newStatus = newStatus;
    }

    @Override
    public void execute() {
        Optional<Task> taskOptional = repository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.updateStatus(newStatus);
            System.out.println("Task " + id + " status updated to " + newStatus.getLabel());
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
}