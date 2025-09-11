package com.hackhud.taskmanager.entity;

import com.hackhud.taskmanager.entity.enums.TaskStatus;

public class Task {

    private final int id;
    private final long createdAt;
    private String title;
    private TaskStatus status;
    private long updatedAt;

    public Task(int id, String title, TaskStatus status, long createdAt, long updatedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void updateTitle(String title) {
        this.title = title;
        this.updatedAt = System.currentTimeMillis();
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + status.getLabel() + ")";
    }
}