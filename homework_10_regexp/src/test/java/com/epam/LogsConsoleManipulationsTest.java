package com.epam;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class LogsConsoleManipulationsTest {
    private LogsConsoleManipulations consoleManipulations =
            new LogsConsoleManipulations();
    private static final int MAIN_TITLE_LINE_SIZE = 1;

    private static final String FILE_FLAG = "-file";
    private static final String VIEW_FLAG = "-view";
    private static final String SAVE_FLAG = "-save";
    private static final String LOAD_FLAG = "-load";

    @Test
    public void handleLoadingLogsFromFileAndShowIt() {
        //GIVEN
        int linesForOneModuleWithModuleName = 3;
        int countOfDifferentModules = 6;
        String[] parameters = {VIEW_FLAG, "2", FILE_FLAG, "src/test/resources/test-logs.txt"};

        //WHEN
        List<String> logs = consoleManipulations.handleRequest(parameters);

        //THEN
        assertNotNull(logs);
        assertThat(logs, hasSize(MAIN_TITLE_LINE_SIZE +
                (linesForOneModuleWithModuleName * countOfDifferentModules)));
    }

    @Test
    public void handleLoadingLogsFromFileAndSaveResultsToFile() {
        //GIVEN
        String fileNameForSaving = "123.dat";
        String[] parameters = {VIEW_FLAG, "2", FILE_FLAG, "src/test/resources/test-logs.txt", SAVE_FLAG, fileNameForSaving};
        File file = new File(fileNameForSaving);
        boolean existsBeforeRunning = file.exists();

        //WHEN
        consoleManipulations.handleRequest(parameters);

        //THEN
        boolean existsAfterRunning = file.exists();
        assertFalse(existsBeforeRunning);
        assertTrue(existsAfterRunning);

        file.delete();
    }

    @Test
    public void handleLoadingResultsFromDatFile() {
        //GIVEN
        int linesForOneModuleWithoutModuleName = 1;
        int countOfDifferentModules = 1;

        String[] parameters = {VIEW_FLAG, "2", LOAD_FLAG, "src/test/resources/serialized-logs.dat"};

        //WHEN
        List<String> logs = consoleManipulations.handleRequest(parameters);

        //THEN
        assertNotNull(logs);
        assertThat(logs, hasSize(MAIN_TITLE_LINE_SIZE
                + countOfDifferentModules
                + linesForOneModuleWithoutModuleName));
    }
}