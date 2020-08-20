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
    Map<String, String> map1 = new HashMap<>();
    Map<String, List<String>> map2 = new HashMap<>();

    try (BufferedReader in = new BufferedReader(new FileReader(source))) {
        in.lines().forEach(line -> {
          String[] list1 = line.split(":");
          String[] list2 = list1[1].split(",");
            for (int i = 0; i < list2.length; i++) {
                //ключ- имейл пользователя
                String key = list2[i];
                //значение - имя юзера
                String value = list1[0];
                map1.put(key, value);
            }


        });



       for (String key : map1.keySet()) {
            System.out.println((key + " " + map1.get(key)));
       }


    } catch (Exception e) {
        e.printStackTrace();
    }





    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
        for (String key : map1.keySet()) {
           out.println(key + " " + map1.get(key));
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
