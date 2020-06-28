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
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            if (children != user.children) {
                return false;
            }
            if (!name.equals(user.name)) {
                return false;
            }
            return birthday.equals(user.birthday);
        }

    }

    /**
     * В резульатае выведутся два разных значения с разными ключами
     *
     * После создания , объекты имеют разный hashcode, так как выдялются разные ячейки памяти
     * После переопределения equals, объекты равны по equals, но второй объект попадет в другую ячейку массива cсылок Node,
     * так как они имеют разный hash
     * @param args
     */
    public static void main(String[] args) {
        User first = new User("Андрей", 10, new GregorianCalendar(2010, 8, 14));
        User second = new User("Андрей", 10, new GregorianCalendar(2010, 8, 14));
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));
        Map<User, Object> map = new HashMap<>();
        map.put(first,  "one");
        map.put(second, "two");
        System.out.println(map);
    }
}
