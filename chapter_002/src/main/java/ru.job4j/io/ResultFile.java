package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {

    public static void main(String[] args) {
        try (PrintWriter out  = new PrintWriter(new BufferedOutputStream(new FileOutputStream("result.txt")))) {
            for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                out.println((i + "*" + j + "=" + i * j));
            }
        }
    } catch (Exception e) {
           e.printStackTrace();
        }
   }
}
