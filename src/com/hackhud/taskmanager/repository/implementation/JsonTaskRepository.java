package com.hackhud.taskmanager.repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackhud.taskmanager.entity.Task;
import com.hackhud.taskmanager.entity.enums.TaskStatus;
import com.hackhud.taskmanager.repository.TaskRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class JsonTaskRepository implements TaskRepository {

    private final Path filePath;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final List<Task> tasks;

    public JsonTaskRepository(String fileName) {
        this.filePath = Paths.get(fileName);
        ensureJsonFileExists();
        this.tasks = new CopyOnWriteArrayList<>(loadTasksFromFile());
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public boolean remove(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public Optional<Task> findById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public int getNextId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void save() {
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to JSON file", e);
        }
    }

    private List<Task> loadTasksFromFile() {
        try (FileReader reader = new FileReader(filePath.toFile())) {
            Task[] tasksArray = gson.fromJson(reader, Task[].class);
            return tasksArray != null ? new ArrayList<>(Arrays.asList(tasksArray)) : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Error reading from JSON file", e);
        }
    }

    private void ensureJsonFileExists() {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                Files.write(filePath, "[]".getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to create JSON file", e);
            }
        }
    }
}