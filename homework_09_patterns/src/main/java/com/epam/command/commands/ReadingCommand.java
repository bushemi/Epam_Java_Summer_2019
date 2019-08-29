package com.epam.command.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class ReadingCommand implements Command {
    private String fileName;
    private Consumer<String> consumer;

    public ReadingCommand(String fileName, Consumer<String> consumer) {
        this.fileName = fileName;
        this.consumer = consumer;
    }

    @Override
    public void execute() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            bufferedReader.lines().forEach(consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
