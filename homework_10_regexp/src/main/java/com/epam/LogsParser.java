package com.epam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogsParser {
    private static final Pattern PATTERN_FOR_DATE = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{1,10}");
    private static final Pattern PATTERN_FOR_OPERATION = Pattern.compile("Operation\\: ");
    private static final Pattern PATTERN_FOR_EXECUTION_TIME = Pattern.compile("Execution time\\: ");
    private static final Pattern PATTERN_FOR_MODULE = Pattern.compile("Module=");
    private StringParser parser;

    public LogsParser(StringParser parser) {
        this.parser = parser;
    }

    public LogLine parseLogs(String text) {
        LogLine logLine = new LogLine();

        String parsedDate = parseLocalDateTime(text);
        String module = parseModule(text);
        String operation = parseOperation(text);
        String executionTime = parseExecutionTime(text);

        logLine.setDateTime(parsedDate);
        logLine.setModule(module);
        logLine.setOperation(operation);
        logLine.setExecutionTimeMsec(executionTime);
        return logLine;
    }

    private String parseExecutionTime(String text) {
        return parser.findWordAfterRegexp(text, PATTERN_FOR_EXECUTION_TIME, " ");
    }

    private String parseOperation(String text) {
        String endOfOperation = "Execution time";
        return parser.findWordAfterRegexp(text, PATTERN_FOR_OPERATION, endOfOperation);
    }

    private String parseModule(String text) {
        return parser.findWordAfterRegexp(text, PATTERN_FOR_MODULE, " ");
    }

    private String parseLocalDateTime(String text) {
        Matcher matcher = PATTERN_FOR_DATE.matcher(text);
        matcher.find();
        return matcher.group();
    }
}
