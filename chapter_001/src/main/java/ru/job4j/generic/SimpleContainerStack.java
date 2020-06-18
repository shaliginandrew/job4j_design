package ru.job4j.generic;

public interface SimpleContainerStack<E> extends Iterable<E> {
    void push(E e);
    void pop(E e);
}
