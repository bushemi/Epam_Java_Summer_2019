package com.epam.templateMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractWriter {
    private String fileName;

    public AbstractWriter(String fileName) {
        this.fileName = fileName;
    }

    abstract List<String> getTextForWriting();

    private Path getPath() {
        return Paths.get(fileName);
    }

    public void writeToFile()  {
        Path path = getPath();
        try {
            Files.write(path, getTextForWriting());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
