package com.epam;

import java.util.*;
import java.util.stream.Collectors;

public class LogsViewer {

    public String createResultForView(LogLine line) {
        return String.format("%s %s ms, finished at %s", line.getOperation(),
                line.getExecutionTimeMsec(), line.getDateTime());
    }

    public Map<String, Set<LogLine>> getLongestOperationsByModule(List<LogLine> list, int count) {
        Map<String, Set<LogLine>> result = new HashMap<>();
        list.forEach(line -> includeLogToMap(result, line));
        limitResults(result, count);
        return result;
    }

    private void includeLogToMap(Map<String, Set<LogLine>> map, LogLine line) {
        Set<LogLine> strings = map.getOrDefault(line.getModule(),
                new TreeSet<>());
        strings.add(line);
        map.put(line.getModule(), strings);
    }

    private void limitResults(Map<String, Set<LogLine>> result, int count) {
        Set<String> keys = result.keySet();
        for (String key : keys) {
            result.compute(key, (keyNotNeeded, value) ->
                    new TreeSet<>(result.get(key).stream().limit(count).collect(Collectors.toSet())));
        }
    }
}
