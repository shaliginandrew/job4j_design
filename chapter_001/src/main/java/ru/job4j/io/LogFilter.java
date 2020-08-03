package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
        List<String> lines = new ArrayList<String>();
        List<String> lines404 = new ArrayList<String>();
        in.lines().forEach(lines :: add);
        int count2 = 0;
        for (String line : lines) {
            int count = 0;
            count2++;
            for (String s1 : line.split(" ")) {
            if (count == 8) {
                if (Integer.parseInt(s1) == 404) {
                   // System.out.println(s1);
                   //System.out.println(count2);
                  // lines.get(count2);
                    System.out.println(lines.get(count2 - 1));
                }
            }
            count++;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
