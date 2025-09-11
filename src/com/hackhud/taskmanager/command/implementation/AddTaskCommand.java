package com.hackhud.taskmanager.command.implementation;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.entity.Task;
import com.hackhud.taskmanager.entity.enums.TaskStatus;
import com.hackhud.taskmanager.repository.TaskRepository;

public class AddTaskCommand implements Command {
    private final TaskRepository repository;
    private final String title;

    public AddTaskCommand(TaskRepository repository, String title) {
        this.repository = repository;
        this.title = title;
    }

    @Override
    public void execute() {
        int newId = repository.getNextId();
        long now = System.currentTimeMillis();
        Task task = new Task(newId, title, TaskStatus.TODO, now, now);
        repository.add(task);
        System.out.println("Task added with ID: " + newId);
    }
}