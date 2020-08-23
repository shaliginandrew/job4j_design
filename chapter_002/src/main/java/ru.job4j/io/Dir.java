package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dir {
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        long sizeParentFolder = Files.walk(file.toPath())
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
        System.out.println(file + " " + sizeParentFolder);
        for (File subfile : file.listFiles()) {
            long sizeChildrenFolder = Files.walk(subfile.toPath())
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();

            System.out.println(subfile + " " + sizeChildrenFolder);
        }
    }
}