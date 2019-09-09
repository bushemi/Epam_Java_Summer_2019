package com.epam.serializer;

import com.epam.LogLine;

import java.util.List;

public interface LogsToFileSerializerService {
    void serialize(String fileName, List<LogLine> lines);

    List<LogLine> deserialize(String fileName);
}
