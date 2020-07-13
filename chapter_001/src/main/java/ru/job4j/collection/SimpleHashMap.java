package ru.job4j.collection;


import org.w3c.dom.Node;

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
public class SimpleHashMap<K, V> implements Iterable<V> {

    private int size;
    private int modCount;
    private Node<K, V>[] hashTable;

    public SimpleHashMap() {
        hashTable = new Node[16];
    }

    static class Node<K, V> {
        private List<Node<K, V>> nodes;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K, V>>();
        }
    }

    boolean insert(K key, V value) {
        int hash = key.hashCode() % size;
        if (hashTable[hash] == null) {
            hashTable[hash].value = value;
            size++;
            modCount++;
        }
        return true;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public V next() {
                return null;
            }
        };
    }
}
