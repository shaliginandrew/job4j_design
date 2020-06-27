package ru.job4j.collection;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
    }
    @Test
    /**
     * Создаем два объекта одного класса с одинаковыми значениями полей, у двух объектов генерируются разные hashcode
     * Создаем карту с реализацией HashMap(ключ- объект тип User, значение Object). Изначально размер hashmap равен 16.
     *
     * При добавлении пеового элемента, последовательность шагов следующая:
     * 1.Сначала ключ проверяется на равенство null. Если это проверка вернула true, будет вызван метод с добавлением null-ключа
     * putForNullKey(value)
     * 2.Далее генерируется хэш на основе ключа побитовым сдвигом. Для генерации используется хэш-функция: метод hash(hashCode),
     * в который передается key.hashCode().
     * 3.С помощью метода indexFor(hash, tableLength), определяется позиция в массиве, куда будет помещен элемент.
     * 4. Теперь, зная индекс в массиве, мы получаем список (цепочку) элементов, привязанных к этой ячейке.
     * Хэш и ключ нового элемента поочередно сравниваются с хэшами и ключами(через equals) элементов из списка и, при совпадении этих параметров,
     * значение элемента перезаписывается.
     * 5.Если же предыдущий шаг не выявил совпадений, будет вызван метод addEntry(hash, key, value, index) для добавления нового элемента.
     */
    public void map() {
        User first = new User("Андрей", 10, new GregorianCalendar(2010, 8, 14));
        User second = new User("Андрей", 10, new GregorianCalendar(2010, 8, 14));
        Map<User, Object> map = new HashMap<>();
        map.put(first,  "one");
        map.put(second, "two");
        System.out.println(map);
    }
}
