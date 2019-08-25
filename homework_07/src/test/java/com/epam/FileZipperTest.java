package com.epam;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileZipperTest {
    private static final String SRC_TEST_RESOURCE = "src/test/resource/";
    private FileZipper zipper = new FileZipperImpl();

    @Test
    public void shouldZipFileOutsideTheFolder() throws IOException {
        //GIVEN
        String source = SRC_TEST_RESOURCE + "shouldZipFileOutsideTheFolder.txt";
        String destination = SRC_TEST_RESOURCE + "shouldZipFileOutsideTheFolder.zip";
        File file = new File(destination);
        boolean beforeTest = file.exists();

        //WHEN
        zipper.zip(source, destination);

        //THEN
        assertTrue(file.exists());
        Files.delete(file.toPath());
        assertFalse(beforeTest);
    }

    @Test
    public void shouldUnzipFileOutsideTheFolder() throws IOException {
        //GIVEN
        String source = SRC_TEST_RESOURCE + "shouldUnzipFileOutsideTheFolder.zip";
        String destination = SRC_TEST_RESOURCE + "shouldUnzipFileOutsideTheFolder.txt";
        File file = new File(destination);
        boolean beforeTest = file.exists();

        //WHEN
        zipper.unZip(source);

        //THEN
        assertTrue(file.exists());
        Files.delete(file.toPath());
        assertFalse(beforeTest);
    }

    @Test
    public void shouldZipFileInsideFolderWithFileInsideAnotherFolder() throws IOException {
        //GIVEN
        String source = SRC_TEST_RESOURCE + "folder1";
        String destination = SRC_TEST_RESOURCE + "folder1.zip";
        File file = new File(destination);
        boolean beforeTest = file.exists();

        //WHEN
        zipper.zip(source, destination);

        //THEN
        assertTrue(file.exists());
        Files.delete(file.toPath());
        assertFalse(beforeTest);
    }

    @Test
    public void shouldUnZipFileInsideFolderWithFileInsideAnotherFolder() throws IOException {
        //GIVEN
        String source = SRC_TEST_RESOURCE + "shouldUnZipFileInsideFolderWithFileInsideAnotherFolder.zip";
        String destination = SRC_TEST_RESOURCE + "folder1.2";
        File file = new File(destination);
        boolean beforeTest = file.exists();

        //WHEN
        zipper.unZip(source);

        //THEN
        assertTrue(file.exists());
        Files.walk(file.toPath())
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        assertFalse(beforeTest);
    }
}
