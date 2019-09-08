package com.epam;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class LogsReaderTest {
    private final LogsReader reader = new LogsReader();

    @Test
    public void test() {
        //GIVEN
        String fileName = "src/test/resources/test-logs.txt";

        //WHEN
        List<String> read = reader.read(Paths.get(fileName));

        //THEN
        assertThat(read, hasSize(35));
    }
}