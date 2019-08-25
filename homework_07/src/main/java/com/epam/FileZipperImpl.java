package com.epam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;

public class FileZipperImpl implements FileZipper {


    private List<File> getListOfFiles(File file) {
        List<File> fileList = new ArrayList<>();
        if (!file.exists()) return fileList;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (isNull(files)) return fileList;
            Stream.of(files)
                    .flatMap(innerFile -> getListOfFiles(innerFile).stream())
                    .forEach(fileList::add);
        }
        fileList.add(file);
        return fileList;
    }

    @Override
    public void zip(String source, String destination) {
        File file = new File(source);
        if (!file.exists()) throw new RuntimeException("source does not lead anywhere!");
        List<File> sources;
        if (file.isDirectory()) {
            sources = getListOfFiles(file);
        } else {
            sources = singletonList(file);
        }
        zipFiles(sources, destination);
    }

    private void zipFiles(List<File> sources, String destination) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(destination))) {
            for (File srcFile : sources) {
                FileInputStream fileInputStream;
                if (srcFile.isDirectory()) {
                    zipOutputStream.putNextEntry(new ZipEntry(srcFile.getPath() + "/"));
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry(srcFile.getPath()));

                    fileInputStream = new FileInputStream(srcFile);
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    zipOutputStream.write(buffer);
                    fileInputStream.close();
                }
                zipOutputStream.closeEntry();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void unZip(String source) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(source))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    writeFile(zin, entry.getName());
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void writeFile(ZipInputStream zin, String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(zin));
        Path path = Paths.get(fileName);
        path.getParent().toFile().mkdirs();
        List<String> lines = reader.lines().collect(Collectors.toList());
        Files.write(path, lines);
    }
}
