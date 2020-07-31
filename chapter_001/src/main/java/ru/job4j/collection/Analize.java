package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
     /**
      * @param previous - предыдущие данные
      * @param current - текущие данные
      * @return - возвращает статистику об изменении коллекции:
      *  Сколько добавлено новых пользователей.
      *  Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
      *  Сколько удалено пользователей.
      */
     public Info diff(List<User> previous, List<User> current) {
         Info info = new Info();
         Map<Integer, User> curMap = current.stream().collect(Collectors.toMap(User :: getId, user -> user));
         for (User item : previous) {
         if (curMap.get(item.id) == null) {
             info.deleted++;
         }
         if (curMap.get(item.id) != null && !curMap.get(item.id).name.equals(item.name)) {
             info.changed++;
             }
         }
         info.added = current.size() + info.deleted - previous.size();
         return info;
        }

        public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }
     }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}