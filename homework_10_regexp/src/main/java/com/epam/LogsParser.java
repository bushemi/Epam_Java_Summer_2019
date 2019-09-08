package com.epam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogsParser {

    public static LogLine parseLogs(String text) {
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

    private static String parseExecutionTime(String text) {
        String regexp = "Execution time\\: ";
        return findWordAfterRegexp(text, regexp, " ");
    }

    private static String parseOperation(String text) {
        String regexp = "Operation\\: ";
        String endOfOperation = "Execution time";
        return findWordAfterRegexp(text, regexp, endOfOperation);
    }

    private static String findWordAfterRegexp(String text, String regexp, String end) {
        Pattern patternOperation = Pattern.compile(regexp);
        Matcher matcher = patternOperation.matcher(text);
        matcher.find();
        int startOfNeededText = matcher.end();

        String fromOperationTillEnd = text.substring(startOfNeededText);
        return fromOperationTillEnd.trim().substring(0, fromOperationTillEnd.indexOf(end)).trim();
    }

    private static String parseModule(String text) {
        String regexpLevel = "Module=";
        return findWordAfterRegexp(text, regexpLevel, " ");
    }

    private static String parseLocalDateTime(String text) {
        Pattern patternDate = getPatternForDate();
        Matcher matcher = patternDate.matcher(text);
        matcher.find();
        return matcher.group();
    }

    private static Pattern getPatternForDate() {
        String regexpDate = "\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{1,10}";
        return Pattern.compile(regexpDate);
    }


}
