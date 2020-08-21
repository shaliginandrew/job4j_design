package ru.job4j.collection;


import java.util.*;

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
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    private Entry<K, V>[] table;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int modCount = 0;
    private int size = 0;


    public SimpleHashMap() {
        this.table = new Entry[capacity];
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<SimpleHashMap.Entry<K, V>> iterator() {

        return new Iterator<SimpleHashMap.Entry<K, V>>() {
            int expectedModCount = modCount;
            private int it = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                for (int i = it; i < table.length; i++) {
                    if (table[i] != null) {
                        result = true;
                        it = i;
                        break;
                    }
                }
                return result;
            }

            @Override
            public SimpleHashMap.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> result = table[it++];
                return result;
            }
        };
    }


    public static class Entry<K, V> {
        private K key;
        private V value;


        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (size / table.length < loadFactor) {
            int hash = key == null ? 0 :  Math.abs(key.hashCode() % capacity);
            if (table[hash] == null) {
                modCount++;
                size++;
                table[hash] = new Entry<>(key, value);
                result = true;
            }
        } else if (resize()) {
            int hash = key == null ? 0 :  Math.abs(key.hashCode() % capacity);
            table[hash] = new Entry<>(key, value);
            size++;
            modCount++;
        }
        return result;
    }

    public boolean resize() {
        boolean result = false;
        if (size / table.length > loadFactor) {
            Entry<K, V>[] oldTable = table;
            size = 0;
            capacity *= 2;
            table = new Entry[capacity];
            for (int i = 0; i < oldTable.length; i++) {
                if (oldTable[i] != null) {
                    insert(oldTable[i].key, oldTable[i].value);
                    result = true;
                }
            }
        }
        return result;
    }

    public V get(K key)  {
        int hash = key == null ? 0 :  Math.abs(key.hashCode() % capacity);
        V value = null;
        if (table[hash] != null && Objects.equals(table[hash].getKey(), key)) {
            value = table[hash].value;
        }
        return value;

    }

    public boolean delete(K key) {
        boolean result = false;
        int hash = key == null ? 0 :  Math.abs(key.hashCode() % capacity);
        if (table[hash] != null && Objects.equals(table[hash].getKey(), key)) {
            table[hash] = null;
            modCount++;
            size--;
            result = true;
        }
        return result;
    }
}