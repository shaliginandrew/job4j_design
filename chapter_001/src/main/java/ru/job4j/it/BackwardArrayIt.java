package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int endPosition;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.endPosition = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return endPosition >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return data[endPosition--];
    }
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}