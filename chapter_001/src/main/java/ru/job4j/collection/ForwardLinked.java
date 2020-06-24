package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Необходимо реализовать метод delete для односвязного списка.
 *  Процесс удаления в такой структуре сводиться к обнулению ссылки на следующий узел.
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head, tail;
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public T get(int index) {
        Node<T> ref = head;
        for (int i = 0; i < index; i++) {
            ref = ref.next;
        }
        return ref.value;
    }

    public void deleteFirst() throws NoSuchElementException {
     if (head == null) {
            throw new NoSuchElementException("Список пуст");
}
     head = head.next;
    }


    public T deleteLast() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        Node<T> ref = head;
        for (int i = 0; i < size; i++) {
        ref = ref.next;
    }
    size--;
    return ref.value;

    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}