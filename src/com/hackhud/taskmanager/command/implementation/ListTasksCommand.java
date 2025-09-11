package com.hackhud.taskmanager.command.implementation;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.entity.enums.TaskStatus;
import com.hackhud.taskmanager.repository.TaskRepository;
import com.hackhud.taskmanager.view.ConsoleView;

import java.util.List;

public class ListTasksCommand implements Command {
    private final TaskRepository repository;
    private final ConsoleView view;
    private final TaskStatus status;

    public ListTasksCommand(TaskRepository repository, ConsoleView view, TaskStatus status) {
        this.repository = repository;
        this.view = view;
        this.status = status;
    }

    @Override
    public void execute() {
        if (status == null) {
            view.displayTasks(repository.findAll(), "All tasks:");
        } else {
            view.displayTasks(repository.findByStatus(status), "Tasks with status " + status.getLabel() + ":");
        }
    }
}