package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchFilesByCondition {


    public static void main(String[] args) throws IOException {
        Path folder = Paths.get(".");
        Files.walk(folder)
                .filter(p -> p.toFile().getName().equals("123.txt")).forEach(System.out::println);

    }
}
