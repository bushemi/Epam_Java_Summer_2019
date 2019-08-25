package com.epam;

import static java.util.Objects.nonNull;

public class ConsoleFileAndFolderArchiver implements FileAndFolderArchiver {
    private FileZipper zipper;

    public ConsoleFileAndFolderArchiver(FileZipper zipper) {
        this.zipper = zipper;
    }

    public void process(String[] arguments) {
        if (nonNull(arguments) && arguments.length > 0) {
            Operations operation = Operations.findByString(arguments[0]);
            String source;
            if (arguments.length > 1 && nonNull(arguments[1])) {
                source = arguments[1];
            } else throw new RuntimeException("second parameter must be a path to source file or folder.");
            switch (operation) {
                case ZIP:
                    String destination;
                    if (arguments.length > 2 && nonNull(arguments[2])) {
                        destination = arguments[2];
                    } else {
                        destination = source + ".zip";
                    }
                    zipper.zip(source, destination);
                    return;
                case UNZIP:
                    zipper.unZip(source);
            }
        }
    }
}
