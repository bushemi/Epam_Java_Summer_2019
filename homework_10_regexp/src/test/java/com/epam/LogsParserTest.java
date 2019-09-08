package com.epam;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class LogsParserTest {
    private LogsParser logsParser = new LogsParser();

    @Test
    public void parseLogs() {
        //GIVEN
        String text = "2018-07-12 08:15:06.837 INFO   Module=MIC Operation: Logout       Execution time: 16654 ms";

        //WHEN
        LogLine logLine = logsParser.parseLogs(text);

        //THEN
        assertThat(logLine.getDateTime(), is(equalTo("2018-07-12 08:15:06.837")));
        assertThat(logLine.getModule(), is(equalTo("MIC")));
        assertThat(logLine.getOperation(), is(equalTo("Logout")));
        assertThat(logLine.getExecutionTimeMsec(), is(equalTo("16654")));
    }

}