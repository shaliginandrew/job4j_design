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
        in.lines().forEach(lines :: add);
        int count404 = 0;
        for (String line : lines) {
            int count = 0;
            count404++;
            for (String s1 : line.split(" ")) {
            if (count == 8) {
                if (Integer.parseInt(s1) == 404) {
                     lines404.add(lines.get(count404 - 1));
                }
            }
            count++;
            }
        }
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
