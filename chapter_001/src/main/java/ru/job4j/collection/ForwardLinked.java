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
    private Node<T> tail;

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

    }

    public T get(int index) {
        Node<T> ref = head;
        for (int i = 0; i < index; i++) {
            ref = ref.next;
        }
        return ref.value;
    }

    /**
     * Все, что надо сделать в данном случае - это пробежаться по исходному списку и последовательно перецепить
     * все его элементы в начало нового списка. Полученный в результате список будет обращением исходного списка
     * Запоминаем следующий node текущего, чтобы продолжить его
     * Устанавливаем ссылку, указывающую на предыдущее
     * Изменям предыдущий на текущий, потому что текущий также правильно установил ссылку
     * Меняем первыую node, которая не правильно устанавливала его ссылку, чтобы быть тем, кто был замечен на первом шаге.
     *           До:
     * Node 1 (Head) -> Node 2 -> Node 3 -> Node 4 (Tail) -> null
     *          После:
     *    null <- Node 1 (Tail) <- Node 2 <- Node 3 <- Node 4 (Head)
     */
    public void revert() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
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
        Node<T> prev = null;
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }
        if (count == 1) {
            T temp = current.value;
            head = null;
            count--;
            return temp;
       }


// встаем на предпоследний элемент
            while (current.next != null) {
                prev = current;
                current = current.next;
            }
            // получаем значение последнего элемента и сохраняем его в temp

// обнуляем ссылку на последний элемент
        prev.next = null;
        count--;
        return current.value;
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