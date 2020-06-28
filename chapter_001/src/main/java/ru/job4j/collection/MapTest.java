package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

public class MapTest {
    public static final class User {
        String name;
        int children;
        Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;

        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + children;
            result = 31 * result + birthday.hashCode();
            return result;
        }
    }

    /**
     * В резульатае выведутся два занчения с разным ключем
     * После переопределения hashcode, объекты имеют одинаковый hashcode, так как их поля имеют одинаковые значения
     * Значит hash функция вычислит для них одинаковые значения, а раз так то и индекс в массиве ссылок на Node будет у них одинаковый
     * В то же время два объекта при создании ссылаются на разные ячеки помяти, соответсвенно не равны по  equals
     * Возникнет коллизия, когда два объекта встанут на один индекс и второй будет связан с первым с помощью укзателя next на следующий
     * @param args
     */
    public static void main(String[] args) {
        User first = new User("Андрей", 10, new GregorianCalendar(2010, 8, 14));
        User second = new User("Андрей", 10, new GregorianCalendar(2010, 8, 14));
        System.out.println(first.name.hashCode());
        System.out.println(first.birthday.hashCode());
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));
        Map<User, Object> map = new HashMap<>();
        map.put(first,  "one");
        map.put(second, "two");
        System.out.println(map);
    }
}
