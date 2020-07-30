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
        info.added = current.size() - previous.size();
        info.deleted = previous.size() - current.size();
        if (previous.size() == current.size()) {
            for (int i = 0; i < previous.size(); i++) {
                if (previous.get(i).id == current.get(i).id && !previous.get(i).name.equals(current.get(i).name)) {
                        info.changed++;
                    }
                }
            }
        return info;
        }

        public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}