package com.hackhud.taskmanager.command.implementation;

import com.hackhud.taskmanager.command.Command;
import com.hackhud.taskmanager.view.ConsoleView;

public class HelpCommand implements Command {
    private final ConsoleView view;

    public HelpCommand(ConsoleView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.displayHelp();
    }
}