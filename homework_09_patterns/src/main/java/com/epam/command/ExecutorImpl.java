package com.epam.command;

import com.epam.command.commands.Command;

public class ExecutorImpl implements Executor {

    @Override
    public void runCommand(Command command) {
        command.execute();
    }
}
