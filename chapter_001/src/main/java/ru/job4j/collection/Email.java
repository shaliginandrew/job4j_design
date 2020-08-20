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
    //Map<String, String> map1 = new HashMap<>();
    Map<String, List<User>> map1 = new HashMap<>();
    //List<String[]> list = new ArrayList<>();
    String read;
    try (BufferedReader in = new BufferedReader(new FileReader(source))) {
        in.lines().forEach(line -> {
          String[] list1 = line.split(":");
          String[] list2 = list1[1].split(",");
            for (int i = 0; i < list2.length; i++) {

                User user = new User(list1[0]);
                String key = list2[i];
                List<User> value = new ArrayList<>();

                //value.add();

               // map1.put(list2[i], );

                }


                //System.out.println(list1[0] + " " + list2[i])
         // for (int i = 0; i < list2[1].length(); i++) {
           //   map1.put(list1[0], list2[i]);
       //   }
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


public static class User {
    private String user;

    public User(String user) {
        this.user = user;
    }
}

    public static void main(String[] args) {
    Email email = new Email();
    email.sort("./chapter_001/src/main/java/ru/job4j/collection/data/email.txt",
            "./chapter_001/src/main/java/ru/job4j/collection/data/emailsort.txt");

    }


}
