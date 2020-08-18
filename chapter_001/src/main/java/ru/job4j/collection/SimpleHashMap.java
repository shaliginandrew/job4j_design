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
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] table;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int modCount = 0;
    private int size = 0;


    public SimpleHashMap() {
        this.table = new Node[capacity];
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<SimpleHashMap.Node<K, V>> iterator() {

        return new Iterator<SimpleHashMap.Node<K, V>>() {
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
            public SimpleHashMap.Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = table[it++];
                return result;
            }
        };
    }


    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
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

            int hash = key == null ? 0 :  key.hashCode() % capacity;

            if (table[hash] == null) {
                modCount++;
                size++;
                table[hash] = new Node<>(key, value);
                result = true;
            }


        } else if (resize()) {
            int hash = key.hashCode() % capacity;
            table[hash] = new Node<>(key, value);
            size++;
            modCount++;

        }
        return result;
    }

    public boolean resize() {
        boolean result = false;
        if (size / table.length > loadFactor) {
            Node<K, V>[] oldTable = table;
            size = 0;
            capacity *= 2;
            table = new Node[capacity];
            for (int i = 0; i < oldTable.length; i++) {
                if (oldTable[i] != null) {
                    insert(oldTable[i].key, oldTable[i].value);
                    result = true;
                }
            }
        }
        return result;
    }

    public V get(K key) throws NoSuchElementException {
        int hash = key == null ? 0 :  key.hashCode() % capacity;
        if (table[hash] == null) {
            throw new NoSuchElementException();
        }
        return table[hash].value;

    }

    public boolean delete(K key) {
        boolean result = false;
        int hash = key == null ? 0 :  key.hashCode() % capacity;
        if (table[hash] == null) {
            throw new NoSuchElementException();
        }
        table[hash] = null;
        size--;
        result = true;
        return result;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("выа1", "а");
        map.insert("выа2ип", "б");
        map.insert("мчпап3", "в");
        map.insert("выам4", "г");

        System.out.println(map.size);
        map.delete("выа2ип");
        System.out.println(map.size);

    }
}