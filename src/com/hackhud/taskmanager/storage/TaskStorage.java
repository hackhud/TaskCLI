package com.hackhud.taskmanager.storage;

import java.io.IOException;
import java.nio.file.*;

public class TaskStorage {

    private static final String FILE_NAME = "tasks.json";

    public static boolean fileExists(Path path) {
        return Files.exists(path);
    }

    public static void createJsonFile(Path path) throws IOException {
        Files.createFile(path);
        Files.write(path, "[]".getBytes());
    }

    public static void ensureJsonFile() {
        Path path = Paths.get(FILE_NAME);

        if (!fileExists(path)) {
            try {
                createJsonFile(path);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create JSON file", e);
            }
        }
    }

    public static Path getFilePath() {
        return Paths.get(FILE_NAME);
    }
}
