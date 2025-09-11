package com.hackhud.taskmanager.command;


public class CommandExecutor {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() {
        if (command != null) {
            command.execute();
        }
    }
}
