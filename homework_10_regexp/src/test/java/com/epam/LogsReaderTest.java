package com.epam;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class LogsReaderTest {
    private final LogsReader reader = new LogsReader();
    static final String LOG_FILE_NAME = "src/test/resources/test-logs.txt";

    @Test
    public void shouldReadLogsFromFile() {
        //WHEN
        List<String> read = reader.read(Paths.get(LOG_FILE_NAME));

        //THEN
        assertThat(read, hasSize(35));
    }
}