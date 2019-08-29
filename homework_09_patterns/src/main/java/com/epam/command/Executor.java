package com.epam.command;

import com.epam.command.commands.Command;

public interface Executor {

    void runCommand(Command command);
}
