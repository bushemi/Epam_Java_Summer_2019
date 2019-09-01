package com.epam.templateMethod;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class CurrentTimeWriterTest {

    @Test
    public void writeDate() {
        //GIVEN
        AbstractWriter writer = new CurrentTimeWriter("target/currentTime.txt");
        File file = new File("target/currentTime.txt");

        //WHEN
        writer.writeToFile();

        //THEN
        assertTrue(file.exists());
        file.delete();
    }

}