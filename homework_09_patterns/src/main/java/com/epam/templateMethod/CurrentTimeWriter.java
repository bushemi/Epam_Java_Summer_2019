package com.epam.templateMethod;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CurrentTimeWriter extends AbstractWriter {

    public CurrentTimeWriter(String fileName) {
        super(fileName);
    }

    @Override
    List<String> getTextForWriting() {
        return Collections.singletonList(LocalDateTime.now().toString());
    }
}
