package ru.job4j.it;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс - реализация итератора, который будет пробегать по вложенными итераторам без копирования данных.
 *  есть два итератора один внешний, другой внутренний. Внешний итерирует по итераторам.
 *  Внутренний по элементам. Изначально внешний итератор имеет ссылку на первый итератор.
 *
 */
public class Converter {
    /**
     * @param it Объект итератор итератор
     * @return Итератор чисел
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

       private  Iterator<Integer> current = Collections.emptyIterator();
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {

                while (it.hasNext() && !current.hasNext()) {
                   current = it.next();
                }
                return current.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }

    public static void main(String[] args) {
        final Iterator<Integer> it;
        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> it2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> it3 = Arrays.asList(7, 8, 9).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        Converter iteratorOfIterators = new Converter();
        it = iteratorOfIterators.convert(its);
        System.out.println(it.next());
    }
}
