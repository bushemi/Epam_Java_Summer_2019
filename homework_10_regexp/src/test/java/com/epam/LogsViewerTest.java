package com.epam;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.epam.LogsReaderTest.LOG_FILE_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

public class LogsViewerTest {
    private LogsViewer logsViewer = new LogsViewer();
    private LogsReader logsReader = new LogsReader();
    private LogsParser logsParser = new LogsParser();

    @Test
    public void createResultForView() {
        //GIVEN
        LogLine logLine = new LogLine();
        logLine.setExecutionTimeMsec("111");
        logLine.setOperation("test");
        logLine.setDateTime("1234-12-34 12:34:56:789");

        //WHEN
        String resultForView = logsViewer.createResultForView(logLine);

        //THEN
        assertThat(resultForView, is(equalTo("test 111 ms, finished at 1234-12-34 12:34:56:789")));
    }

    @Test
    public void getLongestOperationsByModule() {
        //GIVEN
        List<String> logs = logsReader.read(Paths.get(LOG_FILE_NAME));
        List<LogLine> logLines = logs.stream().map(logsParser::parseLogs).collect(Collectors.toList());

        //WHEN
        Map<String, Set<LogLine>> longestOperationsSortedByModule = logsViewer.getLongestOperationsByModule(logLines, 3);

        //THEN
        validateLongestOperation(longestOperationsSortedByModule, "ASC", "Logout", "47342");
        validateLongestOperation(longestOperationsSortedByModule, "LMC", "Save Order", "35899");
        validateLongestOperation(longestOperationsSortedByModule, "CMN", "Login", "42867");
        validateLongestOperation(longestOperationsSortedByModule, "MIC", "Logout", "12258");
        validateLongestOperation(longestOperationsSortedByModule, "LWS", "Login", "44823");
        validateLongestOperation(longestOperationsSortedByModule, "LAB", "Find Product", "47543");
    }

    private void validateLongestOperation(Map<String, Set<LogLine>> longestOperationsSortedByModule,
                                          String moduleName, String operation, String executionTime) {
        Set<LogLine> logLines = longestOperationsSortedByModule.get(moduleName);
        assertNotNull(logLines);
        assertThat(logLines, not(empty()));
        logLines.stream()
                .findFirst()
                .ifPresent(logLine -> {
                    assertThat(logLine.getOperation(), is(equalTo(operation)));
                    assertThat(logLine.getExecutionTimeMsec(), is(equalTo(executionTime)));
                });
    }
}