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
public class Email implements Sort {

@Override
/*

 */
    public void convert(List<User> source) {

    Map<String, String> map1 = new HashMap<>(); // хранит пару mail -user
    Map<String, String> map2 = new HashMap<>(); // храним пару user - user
    Map<String, String> map3 = new HashMap<>();
    for (User user : source) {

    for (String email : user.getEmails()) {

        if (!map1.containsKey(email)) {
            map1.put(email, user.getName());
          //  String value =

        }

        if (map1.containsKey(email) && map1.get(email) != user.getName()) {
           // System.out.println(map1.get(email) + " " + user.getName());
             map2.put(user.getName(), map1.get(email));
        }

    }
    }




    for (String email : map1.keySet()) {
        String key = null;
        if (map2.containsKey(map2.get(map1.get(email)))) {
            map2.get(map2.get(map1.get(email)));
        }


    }

   for (String key : map2.keySet()) {
        System.out.println(key + " " + map2.get(key));
   }


}
    public static void main(String[] args) {
        Email email = new Email();
        List<User> source = Arrays.asList(
                 new User("user1", new HashSet<String>(Arrays.asList(
                         "xxx@ya.ru",
                         "foo@gmail.com",
                         "lol@mail.ru"))),
                new User("user2", new HashSet<String>(Arrays.asList(
                         "foo@gmail.com",
                         "ups@pisem.net"))),
                new User("user3", new HashSet<String>(Arrays.asList(
                        "xyz@pisem.net",
                        "vasya@pupkin.com"))),
                new User("user4", new HashSet<String>(Arrays.asList(
                        "ups@pisem.net",
                        "aaa@bbb.ru"))),

                new User("user5", new HashSet<String>(Arrays.asList(
                        "xyz@pisem.net")))
        );
         email.convert(source);
    }
}
