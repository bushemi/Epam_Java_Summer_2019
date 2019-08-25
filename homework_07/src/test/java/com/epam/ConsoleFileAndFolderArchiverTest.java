package com.epam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleFileAndFolderArchiverTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private FileZipper zipper;

    @InjectMocks
    private ConsoleFileAndFolderArchiver archiver;

    @Test
    public void shouldZipTheFile() {
        //GIVEN
        String source = "source";
        String operation = "zip";

        //WHEN
        archiver.process(new String[]{operation, source});

        //THEN
        verify(zipper).zip(source, source + ".zip");
    }

    @Test
    public void shouldZipTheFileToSpecialNamedZip() {
        //GIVEN
        String source = "source";
        String operation = "zip";
        String destination = "destination.zip";

        //WHEN
        archiver.process(new String[]{operation, source, destination});

        //THEN
        verify(zipper).zip(source, destination);
    }

    @Test
    public void shouldUnZipTheFile() {
        //GIVEN
        String source = "source.zip";
        String operation = "unzip";

        //WHEN
        archiver.process(new String[]{operation, source});

        //THEN
        verify(zipper).unZip(source);
    }

    @Test
    public void shouldThrowUnsupportedOperationException() {
        //GIVEN
        expectedException.expect(UnsupportedOperationException.class);
        expectedException.expectMessage("use zip or unzip!");

        String source = "source";
        String operation = "zap";

        //WHEN
        archiver.process(new String[]{operation, source});
    }

    @Test
    public void shouldThrowParameterNotFound() {
        //GIVEN
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("second parameter must be a path to source file or folder.");

        String operation = "zip";

        //WHEN
        archiver.process(new String[]{operation, null});
    }
}
