package com.epam.command.commands;


import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

public class WritingCommand implements Command {
    private String fileName;
    private Supplier<String> supplier;
    private boolean append;

    public WritingCommand(String fileName, Supplier<String> supplier, boolean append) {
        this.fileName = fileName;
        this.supplier = supplier;
        this.append = append;
    }

    public WritingCommand(String fileName, Supplier<String> supplier) {
        this(fileName, supplier, false);
    }

    @Override
    public void execute() {
        try (FileWriter fileWriter = new FileWriter(fileName, append)) {
            fileWriter.write(supplier.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
