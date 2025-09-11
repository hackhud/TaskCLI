package com.hackhud.taskmanager.entity.enums;

public enum TaskStatus {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private final String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static TaskStatus fromLabel(String label) {
        if (label == null) return null;
        for (TaskStatus status : values()) {
            if (status.getLabel().equalsIgnoreCase(label)) {
                return status;
            }
        }
        return null;
    }
}