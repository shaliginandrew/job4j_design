package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Cоздание динамического контейнера на базе односвязного списка
 * @param <E>
 */
public class SimpleList<E> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int modCount = 0;

    public SimpleList(E value) {
        this.head = new Node<E>(value, null);
        this.tail = head;
    }
    /**
     *
     * Добавление в конец списка:
     * Формируем новый узел из входящего значения
     * Если список пуст, то указываем ссылки начала и конца на новый элемент
     * Иначе "старый" последний элемент теперь ссылается на новый,
     * а в указатель на последний элемент записываем адрес нового элемента
     * @param
     */
    public void add(E value) {
        Node<E> node = new Node(value, null);

        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
      modCount++;

    }

    public E get(int index) {
       Node<E> ref = head;
       for (int i = 0; i < index; i++) {
       ref = ref.next;
       }
       return ref.value;
    }

    static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;

        }
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < modCount;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Node<E> temp = head.next;
                    cursor++;
                    return temp.value;
                }
            }
        };
    }
}
