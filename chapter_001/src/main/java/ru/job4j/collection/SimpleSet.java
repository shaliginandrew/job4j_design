package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    int size;
    int count = 0;

    public SimpleSet(int size) {
        this.size = size;
    }

    private SimpleArray<T> linked = new SimpleArray<T>(size);

    void add(T e) {
        if (count == 0) {
            linked.add(e);
            count++;
        }
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                if (!linked.get(i).equals(e)) {
                    linked.add(e);
                    count++;
                }
            }
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return linked.get(index);
    }

    @Override
    public Iterator<T> iterator()  {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return  count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) linked.get(count++);
            }
        };
    }
}


