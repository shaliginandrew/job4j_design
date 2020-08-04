package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
       List<String> lines404 = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
        List<String> lines = new ArrayList<String>();
        in.lines().filter(line -> line.contains("404")).forEach(line -> {
            int count = line.split(" ").length;
            if (line.split(" ")[count - 2].equals("404")) {
            lines404.add(line);
            }
        });
        } catch (Exception e) {
        e.printStackTrace();
    }
    return lines404;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
