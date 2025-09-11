package com.hackhud.taskmanager.repository;

import com.hackhud.taskmanager.entity.Task;
import com.hackhud.taskmanager.entity.enums.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void add(Task task);
    boolean remove(int id);
    Optional<Task> findById(int id);
    List<Task> findAll();
    List<Task> findByStatus(TaskStatus status);
    int getNextId();
    void save();
}