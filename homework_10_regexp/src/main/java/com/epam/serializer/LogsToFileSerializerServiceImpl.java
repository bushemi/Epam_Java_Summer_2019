package com.epam.serializer;

import com.epam.LogLine;

import java.io.*;
import java.util.List;

public class LogsToFileSerializerServiceImpl implements LogsToFileSerializerService {
    @Override
    public void serialize(String fileName, List<LogLine> lines) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LogLine> deserialize(String fileName) {
        List<LogLine> lines = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            lines = (List<LogLine>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}