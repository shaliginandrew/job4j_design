package ru.job4j.collection;

public class SimpleQueue<T> {
    int incount = 0;
    int outcount = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
/*
 * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
 */
    public T poll() {
        if (outcount == 0) {
            while (incount != 0) {
                out.push(in.pop());
                outcount++;
                incount--;
            }
        }
        outcount--;
        return out.pop();
    }
/*
 Метод push(T value) - помещает значение в конец
 */
    public void push(T value) {
        in.push(value);
        incount++;
    }
}