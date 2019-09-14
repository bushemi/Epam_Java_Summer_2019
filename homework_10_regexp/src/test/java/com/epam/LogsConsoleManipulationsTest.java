package com.epam;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;

public class LogsConsoleManipulationsTest {
    private LogsConsoleManipulations consoleManipulations =
            new LogsConsoleManipulations();
    private static final int MAIN_TITLE_LINE_SIZE = 1;

    @Test
    public void handleLoadingLogsFromFileAndShowIt() {
        //GIVEN
        int linesForOneModuleWithModuleName = 3;
        int countOfDifferentModules = 6;
        String[] parameters = {"-view", "2", "-file", "src/test/resources/test-logs.txt"};

        //WHEN
        List<String> logs = consoleManipulations.handleRequest(parameters);

        //THEN
        assertNotNull(logs);
        assertThat(logs, hasSize(MAIN_TITLE_LINE_SIZE +
                (linesForOneModuleWithModuleName * countOfDifferentModules)));
    }

    @Test
    public void handleLoadingResultsFromDatFile() {
        //GIVEN
        int linesForOneModuleWithoutModuleName = 1;
        int countOfDifferentModules = 1;

        String[] parameters = {"-view", "2", "-load", "src/test/resources/serialized-logs.dat"};

        //WHEN
        List<String> logs = consoleManipulations.handleRequest(parameters);

        //THEN
        assertNotNull(logs);
        assertThat(logs, hasSize(MAIN_TITLE_LINE_SIZE
                + countOfDifferentModules
                + linesForOneModuleWithoutModuleName));
    }
}