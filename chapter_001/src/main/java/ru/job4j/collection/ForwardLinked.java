package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Необходимо реализовать метод delete для односвязного списка.
 *  Процесс удаления в такой структуре сводиться к обнулению ссылки на следующий узел.
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    private int count = 0;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            count++;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;

        }
        count++;
        current.next = node;
        current = node;
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
     count--;
    }


    public T deleteLast() throws NoSuchElementException {
        Node<T> current = head;
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }
        // если остался один элемент
        if (count == 1) {
            T temp = current.value;
            head = null;
            count--;
       }

// встаем на предпоследний элемент
        for (int i = 0; i < count - 2; i++) {
        current = current.next;
        }
// получаем значение последнего элемента и сохраняем его в temp

        T temp = current.next.value;
// обнуляем ссылку на последний элемент
        current.next = null;
        count--;
        return temp;
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