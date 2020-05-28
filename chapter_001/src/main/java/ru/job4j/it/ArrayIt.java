package ru.job4j.it;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс - итератор для одномерного массива чисел
 *
 */
public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Если в итераторе нет элементов и мы вызовем метод next
     * В этом случае итератор должен сгенерировать исключение NoSuchElementException.
     * @return возвращает следующий элемент массива
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}