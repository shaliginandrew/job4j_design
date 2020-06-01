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
        boolean rsl = false;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                rsl = true;
                break;
            }
        }  return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }


    public static void main(String[] args) {
        Iterator it = new EvenIt(new int[] {3, 5, 6, 7});
        System.out.println(it.next());
        System.out.println(it.next());
        //System.out.println(it.next());
       // System.out.println(it.next());
    }
}

