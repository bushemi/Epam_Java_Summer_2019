package com.epam;

public class LogViewer {
    public String createResultForView(LogLine line) {
        return String.format("%s %s ms, finished at %s", line.getOperation(), line.getExecutionTimeMsec(), line.getDateTime());
    }
}
