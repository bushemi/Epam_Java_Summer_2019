package com.epam;

import com.epam.serializer.LogsToFileSerializerService;
import com.epam.serializer.LogsToFileSerializerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LogsConsoleManipulations {

    private final Logger logger = LoggerFactory.getLogger("LogsConsoleManipulations");
    private static final String FILE_FLAG = "-file";
    private static final String VIEW_FLAG = "-view";
    private static final String SAVE_FLAG = "-save";
    private static final String LOAD_FLAG = "-load";
    private StringParser stringParser = new StringParser();
    private LogsParser logsParser = new LogsParser(stringParser);
    private LogsReader reader = new LogsReader();
    private LogsViewer viewer = new LogsViewer();
    private LogsToFileSerializerService serializer = new LogsToFileSerializerServiceImpl();

    public List<String> handleRequest(String[] parameters) {
        String fileNameForLoadingLogs = findFileNameForLoadingLogs(parameters);
        String viewCount = findViewCount(parameters);
        String fileNameForSaving = findFileNameForSaving(parameters);
        String fileNameForLoadingResults = findFileNameForLoadingResults(parameters);

        if (!validateViewCount(viewCount)) {
            return Collections.emptyList();
        }
        int resultCount = Integer.parseInt(viewCount);
        if ((isNull(fileNameForLoadingLogs) && isNull(fileNameForLoadingResults)) ||
                (nonNull(fileNameForLoadingLogs) && nonNull(fileNameForLoadingResults))) {
            logger.error("Укажите один из параметров : -load или -file");
            return Collections.emptyList();
        }

        Map<String, Set<LogLine>> longestOperationsByModule;
        if (nonNull(fileNameForLoadingLogs)) {
            List<LogLine> logLines = reader.read(Paths.get(fileNameForLoadingLogs))
                    .stream()
                    .map(logsParser::parseLogs)
                    .collect(Collectors.toList());
            longestOperationsByModule =
                    viewer.getLongestOperationsByModule(logLines, resultCount);

            if (nonNull(fileNameForSaving)) {
                ArrayList<LogLine> logs = new ArrayList<>();
                longestOperationsByModule.forEach((module, logLineSet) -> logs.addAll(logLineSet));
                serializer.serialize(fileNameForSaving, logs);
            }
        } else {
            List<LogLine> logLines = serializer.deserialize(fileNameForLoadingResults);
            longestOperationsByModule =
                    viewer.getLongestOperationsByModule(logLines, resultCount);
        }

        String fileName = null;
        if (isNull(fileNameForLoadingLogs)) {
            fileName = fileNameForLoadingResults;
        }
        if (isNull(fileNameForLoadingResults)) {
            fileName = fileNameForLoadingLogs;
        }
        List<String> logs = new ArrayList<>();
        logs.add(String.format("Top %s operations in \"%s\" file:", viewCount, fileName));
        addOperationsToLogs(longestOperationsByModule, logs);
        return logs;
    }

    private boolean validateViewCount(String viewCount) {
        if (isNull(viewCount)) {
            logger.error("Укажите сколько вы хотите результатов после параметра -view");
            return false;
        }
        int resultCount;
        try {
            resultCount = Integer.parseInt(viewCount);
        } catch (NumberFormatException e) {
            logger.error("Не валидное число после -view параметра : {}", viewCount);
            return false;
        }
        return (resultCount > 0);
    }

    private void addOperationsToLogs(Map<String, Set<LogLine>> longestOperationsByModule, List<String> logs) {
        longestOperationsByModule.forEach((module, logLineSet) -> {
            logs.add(String.format("%s Module:", module));
            logLineSet.stream()
                    .map(viewer::createResultForView)
                    .forEach(logs::add);
        });
    }

    private String findParameterAfterNeededFlag(String[] parameters, String neededFlag, List<String> notNeededFlags) {
        boolean found = false;
        for (String parameter : parameters) {
            if (found) {
                if (notNeededFlags.contains(parameter)) {
                    found = false;
                    continue;
                }
                return parameter;
            }
            if (parameter.equals(neededFlag)) {
                found = true;
            }
        }
        return null;
    }

    private String findFileNameForLoadingLogs(String[] parameters) {
        return findParameterAfterNeededFlag(parameters, FILE_FLAG, Arrays.asList(VIEW_FLAG, SAVE_FLAG, LOAD_FLAG));
    }

    private String findViewCount(String[] parameters) {
        return findParameterAfterNeededFlag(parameters, VIEW_FLAG, Arrays.asList(FILE_FLAG, SAVE_FLAG, LOAD_FLAG));
    }

    private String findFileNameForSaving(String[] parameters) {
        return findParameterAfterNeededFlag(parameters, SAVE_FLAG, Arrays.asList(FILE_FLAG, VIEW_FLAG, LOAD_FLAG));
    }

    private String findFileNameForLoadingResults(String[] parameters) {
        return findParameterAfterNeededFlag(parameters, LOAD_FLAG, Arrays.asList(FILE_FLAG, SAVE_FLAG, VIEW_FLAG));
    }

}
