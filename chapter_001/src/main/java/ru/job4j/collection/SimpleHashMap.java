package ru.job4j.collection;


import java.util.*;
import java.util.function.Predicate;

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
    private Node<K, V>[] table;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int modCount = 0;
    private int size = 0;
    int k = 0;
    int m = 0;



    public SimpleHashMap() {
        this.table = new Node[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    @Override
    public Iterator<V> iterator() throws ConcurrentModificationException {
        int expectedModCount = modCount;


        return new Iterator<V>() {


            V result2 = null;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = m; i < table.length; i++) {
                    if (table[i] != null) {
                        result = true;
                        m = (i + 1);
                        break;
                    }
                }

                return result;
            }
            @Override
            public V next() {
                if (!hasNext()) {
                  throw new NoSuchElementException();
                }
                for (int i = k; i < table.length; i++) {
                    if (table[i] != null) {
                        result2 = table[i].value;
                        k = (i + 1);
                        break;
                    }
                }
                return result2;
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
     *
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
                size++;
                table[hash] = new Node<>(key, value, null);
                result = true;
            }
        } else if (size > table.length * loadFactor) {
           resizeAdd(key, value);
        }
        return result;
    }

    public boolean resizeAdd(K key,  V value) {
        boolean result = false;
        Node<K, V>[] oldTable = table;
        capacity *= 2;
        table = new Node[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                int hash = key.hashCode() % capacity;
                if (table[hash] == null) {
                    this.modCount++;
                    size++;
                    table[hash] = new Node<>(key, value, null);
                    result = true;
                    break;
                }
            }
        } return result;
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "а");
        map.insert(2, "б");
        map.insert(3, "в");
        map.insert(4, "г");
        map.insert(5, "д");
        map.insert(6, "е");
        map.insert(7, "ж");
        map.insert(8, "з");
        map.insert(9, "к");
        map.insert(10, "л");
        map.insert(11, "м");
        map.insert(12, "н");
        map.insert(13, "о");
        map.insert(14, "п");
        map.insert(15, "р");
        map.insert(16, "с");
        map.insert(17, "т");
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
        System.out.println(map.iterator().next());
    }
}