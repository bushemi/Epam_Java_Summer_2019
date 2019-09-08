package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogsReader {

    public List<String> read(Path path) {
        List<String> records = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
