package ru.job4j.collection;

public class SimpleQueue<T> {
    int count = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
/*
 * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
 */
    public T poll() {
       while (count != 0) {
           out.push(in.pop());
           count--;
       }
        return out.pop();
    }
/*
 Метод push(T value) - помещает значение в конец
 */
    public void push(T value) {
        in.push(value);
        count++;
    }
}