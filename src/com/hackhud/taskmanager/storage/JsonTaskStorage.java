package com.hackhud.taskmanager.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackhud.taskmanager.entity.Task;
import com.hackhud.taskmanager.storage.TaskStorage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTaskStorage {

    private static Path path = TaskStorage.getFilePath();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Task> readTasksFromJSON() {
        try (FileReader reader = new FileReader(path.toFile())) {
            Task[] tasksArray = gson.fromJson(reader, Task[].class);
            return tasksArray != null ? new ArrayList<>(Arrays.asList(tasksArray)) : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public static void writeTasksToJSON(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }
}
