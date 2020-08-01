package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try {
            int read;
            boolean rsl = false;
            int number;
            FileInputStream in = new FileInputStream("even.txt");
            StringBuilder text = new StringBuilder();
                while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
                for (String line : lines) {
                number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    rsl = true;
                }
                System.out.println(rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
