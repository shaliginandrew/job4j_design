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
            List<String> sourceList = new ArrayList<String>();
            sourceList = in.lines().filter(line -> line.length() > 0).collect(Collectors.toList());
            for (int i = 0; i < sourceList.size(); i++) {
                if (sourceList.get(i).contains("400") || sourceList.get(i).contains("500")) {
                    for (int j = i + 1; j < sourceList.size(); j++) {
                        if (!(sourceList.get(j).contains("400") || sourceList.get(j).contains("500"))) {
                            targetList.add(sourceList.get(i).split(" ")[1] + ";" + sourceList.get(j).split(" ")[1]);
                            i = j;
                            break;
                        }
                    }
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out  = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String list : targetList) {
                out.println(list);
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
