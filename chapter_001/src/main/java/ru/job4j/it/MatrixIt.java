package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс - итератор для двумерного массива, который последовательно возращает элементы
 *  @autor Andrey Shalygin
 *  @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;

    }
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < data[row].length; column++) {
                if (data[row].length != 0) {
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][column++];
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return rsl;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        int[][] in = {
                {1, 2},
                {3, 4}
        };
        MatrixIt it = new MatrixIt(in);
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
    }
}