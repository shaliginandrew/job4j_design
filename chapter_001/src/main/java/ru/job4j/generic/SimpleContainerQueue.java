package ru.job4j.generic;

public interface SimpleContainerQueue<E> extends Iterable<E> {
    void add(E e);
    E peek();
}
