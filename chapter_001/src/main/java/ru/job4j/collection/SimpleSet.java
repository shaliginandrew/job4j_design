package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать void add(E e) и реализовывать Iterable<E>.
 * Коллекция не должна хранить дубликаты.
 * Set - внутри для хранения данных использует обычные массивы.
 * Код будет идентичным, как и в SimpleArray(Это код из задания реализации списка на массиве).
 * как можно за счет композиции сократить количества кода?
 * Здесь нужно использовать SimpleArray в реализации SimpleSet.
 * @param <T>
 */
public class SimpleSet<T> implements Iterable<T>  {
     int size;
     int count;

    public SimpleSet(int size) {
        this.size = size;
    }

    private SimpleArray<T> link = new SimpleArray<T>(size);

    void add(T e) {
        boolean flag = true;
        for (int i = 0; i < count; i++) {
            if (e.equals(link.get(i))) {
                flag = false;
                return;
            }
        }
        if (true) {
            link.add(e);
            count++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return link.iterator();
    }
}


