package ru.job4j.collection;


import org.w3c.dom.Node;

import java.security.Key;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализовать собственную структуру данных - HashMap [#294199]
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 * boolean insert(K key, V value);
 * V get(K key);
 * boolean delete(K key);
 *
 * Реализовывать итератор.
 * Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение.
 * Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
 *
 * Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.
 */
public class SimpleHashMap<K, V> implements Iterable<V> {
 private int size;
 private int defaultCapacity = 16;
 private MyEntry<K, V>[] values;

    public SimpleHashMap(int size) {
        this.size = size;
        this.values = new MyEntry[defaultCapacity];
    }

    public MyEntry<K, V>[] getValues() {
        return values;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int counterArray = 0;
            int valuesCounter = 0;
            Iterator<Node<K, V>> subiIterator = null;

            @Override
            public boolean hasNext() {
                if (valuesCounter == size) {
                    return false;
                }

if (subiIterator == null || !subiIterator.hasNext()) {
if (moveToNextCell()) {
   // subiIterator = values[counterArray].getValue().
} else {
    return false;
}
}
                return subiIterator.hasNext();
            }



            private boolean moveToNextCell() {
                counterArray++;
                while (counterArray < values.length && values[counterArray] == null) {
                    counterArray++;
                }
                return counterArray < values.length && values[counterArray] != null;
            }

            @Override
            public V next() {
                valuesCounter++;
                return subiIterator.next().value;
            }
        };
    }

    static class Node<K, V> {
        private List<Node<K, V>> nodes;
        private int hash;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K, V>>();
        }

    }



    static class MyEntry<K, V> {
        private final K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }



    boolean insert(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i].setValue(value);
                insert = false;
            }
        }
        ensureCapa();
        values[size++] = new MyEntry<K, V>(key, value);
        return insert;
    }

    private void ensureCapa() {
        if (size == values.length) {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }


    V get(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getKey().equals(key)) {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }


    boolean delete(K key) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i] = null;
                size--;
                result = true;
                condenseArray(i);
            }
        }
         return result;
    }

    private void condenseArray(int start) {
        for (int i = start; i < size; i++) {
            values[i] = values[i + 1];
        }
    }

}
