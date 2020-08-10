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
public class SimpleHashMap<K, V> implements Iterable<K> {
    private Node<K, V>[] table;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int modCount = 0;
    private int size = 0;
    int k = 0;

    public SimpleHashMap() {
        this.table = new Node[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    @Override
    public Iterator<K> iterator() throws ConcurrentModificationException {
        int expectedModCount = modCount;

        return new Iterator<K>() {

            boolean result = false;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                for (int i = k; i < table.length; i++) {
                    if (table[i] != null) {

                        result = true;
                        k = i + 1;
                        break;
                    }
                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return null;
            }
        };
    }

    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    /**
     * Метод добавления:
     * 1.
     *
     * @param key
     * @param value
     * @return
     */

    boolean insert(K key, V value) {
        boolean result = false;
        if (size <= table.length * loadFactor) {
            int hash = key.hashCode() % capacity;
            if (table[hash] == null) {
                this.modCount++;
                table[hash] = new Node<>(key, value, null);
                //System.out.println(table[hash].key);
              //  System.out.println(table[hash].value);
                result = true;
            }
        } else if (size > table.length * loadFactor) {
            Node<K, V>[] oldTable = table;
            size = 0;
            capacity *= 2;
            table = new Node[capacity];
            for (int i = 0; i < oldTable.length; i++) {
                if (oldTable[i] != null) {
                    int hash = key.hashCode() % capacity;
                    if (table[hash] == null) {
                        this.modCount++;
                        table[hash] = new Node<>(key, value, null);
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "а");
        map.insert(2, "б");
        map.insert(3, "в");
        System.out.println(map.iterator().hasNext());
        System.out.println(map.iterator().hasNext());
        System.out.println(map.iterator().hasNext());
        System.out.println(map.iterator().hasNext());
        System.out.println(map.iterator().hasNext());

    }
}