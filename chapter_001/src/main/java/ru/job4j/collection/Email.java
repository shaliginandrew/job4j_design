package ru.job4j.collection;

import java.io.*;
import java.util.*;

/**
 * Имеется n пользователей, каждому из них соответствует список email-ов
 * (всего у всех пользователей m email-ов).
 * Считается, что если у двух пользователей есть общий email, значит это
 * один и тот же пользователь. Требуется построить
 * и реализовать алгоритм, выполняющий слияние пользователей. На выходе
 * должен быть список пользователей с их email-ами (такой же как на
 * входе).
 * В качестве имени объединенного пользователя можно брать любое из
 * исходных имен. Список email-ов пользователя должен содержать только
 * уникальные email-ы.
 * Параметры n и m произвольные, длина конкретного списка email-ов никак
 * не ограничена.
 * Требуется, чтобы асимптотическое время работы полученного решения было
 * линейным, или близким к линейному.
 */
public class Email {

public void sort(String source, String target) {
    Map<String, String> sourceMap = new HashMap<>();
    Map<String, List<String>> targetMap = new HashMap<>();
    List<String> email = new ArrayList<>();
    String read;
    try (BufferedReader in = new BufferedReader(new FileReader(source))) {
        in.lines().forEach(line -> {

            String[] list = line.split(":");
            //String[] list2 = list[1].split(",");
            email.add(list[1]);
            sourceMap.put(list[1], list[0]);
            targetMap.put(list[0], email);
        });









    } catch (Exception e) {
        e.printStackTrace();
    }

    for (String key : sourceMap.keySet()) {
        boolean flag1 = true;
        boolean flag2 = false;
        int i = 0;

        if (flag1) {
            String[] list2 = email.get(i).split(",");
            if (flag2) {

                for (int k = 0; k < list2.length; k++) {
                    if (key.contains(list2[k])) {
                        System.out.println(sourceMap.get(key));
                    }

                }
            }


        }
        i++;
        flag1 = false;
        flag2 = true;
    }


    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
        for (String key : sourceMap.keySet()) {
            String value = sourceMap.get(key);
            out.println(key + ":" + value);
        }


    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public static void main(String[] args) {
    Email email = new Email();
    email.sort("./chapter_001/src/main/java/ru/job4j/collection/data/email.txt",
            "./chapter_001/src/main/java/ru/job4j/collection/data/emailsort.txt");

    }
}
