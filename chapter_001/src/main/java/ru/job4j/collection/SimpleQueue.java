package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
/*
 * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
 */
    public T poll() {
        out.reverse();
        return out.pop();
    }
/*
 Метод push(T value) - помещает значение в конец
 */
    public void push(T value) {
        out.push(value);
    }
}