package ru.job4j.io;

import java.io.File;

/**
 * Программа выводит название директорий папки c:\projects и их размер
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        long length = 0;
        for (File subfile : file.listFiles()) {

                length += subfile.length();


            System.out.println(subfile.getName() + " : " + length);
        }
    }
}
