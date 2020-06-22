package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static java.util.Objects.checkIndex;
import static java.util.Objects.isNull;

/**
 * Cоздание динамического контейнера на базе связного списка
 * @param <E>
 */
public class Node<E> implements Iterable<E> {
    Node<E> next;
    E value;
    int modCount = 0;

    private Node<E> head;
    private Node<E> tail;

    public Node(E value) {
        this.value = value;
    }
    /**
     * Добавление в конец списка.
     * Если список пуст, то указываем ссылки начала и конца на новый элемент
     * Иначе "старый" последний элемент теперь ссылается на новый,
     * а в указатель на последний элемент записываем адрес нового элемента
     *
     * @param value
     */
    public void add(E value) {
        this.modCount++;
        Node<E> a = new Node<E>(value);
        a.value = value;
        if (tail == null) {
            head = a;
            tail = a;
        } else {
           tail.next = a;
           tail = a;
        }
    }

    public E get(int index) {
        Node<E> rsl = head;
        int start = 0;
        while (rsl.next != null) {
            start++;
            if (start == index) {
                return rsl.value;
            }
        }
        return null;
    }


    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException {
        int expectedModCount = modCount;
        return (Iterator<E>) new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) value;
            }
        };
    }
}
