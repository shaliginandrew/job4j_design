package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder or etx not set.");
        }
        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
     Predicate<Path> condition = p -> p.toFile().getName().endsWith(ext);
     SearchFiles seacher = new SearchFiles(condition);
     Files.walkFileTree(root, seacher);
     return seacher.getFiles();
    }
}