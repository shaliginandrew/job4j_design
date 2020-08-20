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


    public void convert(List<User> source) {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (int i = 0; i < source.size(); i++) {
            String[] list1 = source.get(i).getUser().split(":");
            String[] list2 = list1[1].split(",");
            for (int j = 0; j < list2.length; j++) {
                //ключ- имейл пользователя
                String key = list2[j];
                //значение - имя юзера
                String value = list1[0];
                map1.put(key, value);
            }
        }

        for (String key : map1.keySet()) {
            System.out.println(key + " " + map1.get(key));
        }


    }


    public static void main(String[] args) {
        Email email = new Email();

        List<User> source = Arrays.asList(
        new User("user1:xxx@ya.ru,foo@gmail.com,lol@mail.ru"),
        new User("user2:foo@gmail.com,ups@pisem.net"),
        new User("user3:xyz@pisem.net,vasya@pupkin.com"),
        new User("user4:ups@pisem.net,aaa@bbb.ru"),
        new User("user5:xyz@pisem.net")
        );

       email.convert(source);
    }
}
