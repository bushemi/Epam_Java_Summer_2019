package com.epam;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class LogsViewerTest {
    LogsViewer logViewer = new LogsViewer();

    @Test
    public void createResultForView() {
        //GIVEN
        LogLine logLine = new LogLine();
        logLine.setExecutionTimeMsec("111");
        logLine.setOperation("test");
        logLine.setDateTime("1234-12-34 12:34:56:789");

        //WHEN

        String resultForView = logViewer.createResultForView(logLine);

        //THEN
        assertThat(resultForView, is(equalTo("test 111 ms, finished at 1234-12-34 12:34:56:789")));
    }

}