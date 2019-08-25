package com.epam;

public class App {

    public static void main(String[] args) {
        FileZipper zipper = new FileZipperImpl();
        FileAndFolderArchiver archiver = new ConsoleFileAndFolderArchiver(zipper);
        archiver.process(args);
    }
}
