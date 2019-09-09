package com.epam.serializer;

import com.epam.LogLine;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogsToFileSerializerServiceImplTest {
    private LogsToFileSerializerService serializer = new LogsToFileSerializerServiceImpl();

    @Test
    public void serialize() {
        //GIVEN
        LogLine line = new LogLine();
        line.setDateTime("123");
        line.setModule("ABC");
        line.setOperation("login");
        line.setExecutionTimeMsec("567");

        final String fileName = "target/logs.dat";
        File file = new File(fileName);
        boolean logExistBeforeSerialization = file.exists();

        //WHEN
        serializer.serialize(fileName, Arrays.asList(line, line));

        //THEN
        boolean logExistAfterSerialization = file.exists();
        assertFalse(logExistBeforeSerialization);
        assertTrue(logExistAfterSerialization);
        file.delete();
    }

    @Test
    public void deserialize() {
        //WHEN
        List<LogLine> deserializedLines = serializer.deserialize("src/test/resources/serialized-logs.dat");

        //THEN
        for (LogLine line : deserializedLines) {
            assertThat(line.getExecutionTimeMsec(), is(equalTo("567")));
            assertThat(line.getDateTime(), is(equalTo("123")));
            assertThat(line.getOperation(), is(equalTo("login")));
            assertThat(line.getModule(), is(equalTo("ABC")));
        }
    }
}