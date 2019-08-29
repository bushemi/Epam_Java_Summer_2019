package com.epam.command;

import com.epam.command.commands.Command;
import com.epam.command.commands.ReadingCommand;
import com.epam.command.commands.WritingCommand;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ExecutorImplTest {
    private Executor executor = new ExecutorImpl();
    private static final String PATH_TO_TEST_FOLDER = "target/";
    private static final String PATH_TO_TEST_RESOURCES_FOLDER = "src/test/resources/";

    @Test
    public void shouldCreateAndWriteFile() {
        //GIVEN
        String fileName = PATH_TO_TEST_FOLDER + "test.txt";
        Command command = new WritingCommand(fileName, () -> "12345");
        File testFile = new File(fileName);
        boolean existsBeforeTest = testFile.exists();

        //WHEN
        executor.runCommand(command);

        //THEN
        boolean existsAfterTest = testFile.exists();
        assertFalse(existsBeforeTest);
        assertTrue(existsAfterTest);
        testFile.delete();
    }

    @Test
    public void shouldReadFile() {
        //GIVEN
        String fileName = PATH_TO_TEST_RESOURCES_FOLDER + "readingFile.txt";
        StringBuilder builder = new StringBuilder();
        Command command = new ReadingCommand(fileName, builder::append);

        //WHEN
        executor.runCommand(command);

        //THEN
        assertThat(builder.toString(), is(equalTo("You will be next.You are next.")));
    }

}