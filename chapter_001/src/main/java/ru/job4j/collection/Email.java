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
            email.add(list[1]);
            sourceMap.put(list[1], list[0]);
           // targetMap.put(list[0], email);
        });

        for (String key : sourceMap.keySet()) {


        }



    } catch (Exception e) {
        e.printStackTrace();
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
