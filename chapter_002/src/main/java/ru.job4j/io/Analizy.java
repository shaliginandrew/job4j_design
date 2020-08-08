package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    /**
     * Метод anavailable должен находить диапазоны, когда сервер не работал.
     * @param source - имя файла лога
     * @param target - имя файла для записи результатов анализа доступности сервера
     */
    public void unavailable(String source, String target) {
        List<String> targetList = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String read;
            boolean flagStart = false;
            boolean flagEnd = false;
            String[] lineStart = null;
            String[] lineEnd = null;
            while ((read = in.readLine()) != null) {
                if (read.length() > 0) {
                    if ((read.contains("400") || read.contains("500")) && (!flagStart)) {
                        flagStart = true;
                        lineStart = read.split(" ");
                    }
                    if (!(read.contains("400") || read.contains("500")) && flagStart) {
                        flagEnd = true;
                        lineEnd = read.split(" ");
                    }
                    if (flagStart && flagEnd) {
                        targetList.add(lineStart[1] + ";" + lineEnd[1]);
                        flagStart = false;
                        flagEnd = false;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
}
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String line : targetList) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}
