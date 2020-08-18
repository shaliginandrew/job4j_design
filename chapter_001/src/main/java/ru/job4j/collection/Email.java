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
    Map<String, String> map2 = new HashMap<>();
    List<String> email = new ArrayList<>();
    List<String> email2 = new ArrayList<>();
    List<String> email3 = new ArrayList<>();
    String read;
    try (BufferedReader in = new BufferedReader(new FileReader(source))) {
       in.lines().forEach(line -> {
          //String[] list = line.split(":");
          email.add(line);
       });



    } catch (Exception e) {
        e.printStackTrace();
    }

 for (int i = 0; i < email.size(); i++) {



     for (int j = 1; j < email.size(); j++) {



         String[] list = email.get(i).split(":");
         if (!email2.contains(list[1])) {
             email2.add(list[1]);
             email2.add(list[0]);
         }

     }
 }


for (int i = 0; i < email2.size(); i += 2) {
    email3.add(String.join(":", email2.get(i + 1), email2.get(i)));
}


    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
        for (String list : email3) {
           out.println(list);
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
