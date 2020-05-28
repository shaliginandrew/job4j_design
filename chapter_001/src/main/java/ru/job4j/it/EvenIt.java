package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс - итератор, возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 *  @autor Andrey Shalygin
 *  @version 1.0
 */
public class EvenIt implements Iterator<Integer> {

    private final int[] numbers;
    private int point = 0;

    public EvenIt(final int[] numbers) {
    this.numbers = numbers;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return numbers[point++] % 2 == 0 && point <= numbers.length ? true : false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (hasNext()) {
            point++;
        }
          return numbers[point];
    }


    public static void main(String[] args) {
        Iterator it = new EvenIt(new int[] {4, 4, 5, 8});
        System.out.println(it.next());
        //System.out.println(it.next());
        //System.out.println(it.next());
       // System.out.println(it.next());
    }
}

