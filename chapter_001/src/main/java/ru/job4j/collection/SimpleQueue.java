package ru.job4j.collection;

public class SimpleQueue<T> {
    int count = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
/*
 * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
 */
    public T poll() {
        for (int i = 0; i < count - 1; i++) {
            in.push(out.pop());
        }
        count--;
        T rsl = out.pop();
        for (int i = 0; i < count; i++) {
            out.push(in.pop());
        }
        return rsl;
    }
/*
 Метод push(T value) - помещает значение в конец
 */
    public void push(T value) {
        out.push(value);
        count++;

    }
}