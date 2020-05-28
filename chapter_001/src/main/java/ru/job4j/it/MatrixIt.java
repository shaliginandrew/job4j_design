package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;

    }
    @Override
    public boolean hasNext() {
        return row < data.length && column < data[row].length;
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