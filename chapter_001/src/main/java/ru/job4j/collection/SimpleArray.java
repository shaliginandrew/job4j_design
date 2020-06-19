package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.checkIndex;
import static java.util.Objects.isNull;

/**
 * Cоздание реализации ArrayList. ArrayList - это массив.
 * Когда элементов становиться больше чем ячеек в массиве ArrayList создает новый массив с большим размером.
 * Внутри контейнер должен базироваться на массиве Object[] container.
 * Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.
 * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора в коллекцию добавили новый элемент, итератор должен кидать
 * ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно модифицирует коллекцию должна увеличивать этот счетчик.
 * В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount),
 * а затем на каждой итерации сравнивает сохраненное значение, с текущим значением поля modCount, если они отличаются, то генерируется исключение.
 * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов. Например, у вас есть хранилище
 * из 10 элементов. Вы добавили 3 элемента. Каким может быть индекс? [0, 2]. Для проверки индекса используйте метод Objects.checkIndex.
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int modCount = 0;

    public SimpleArray(int size) {
        this.container = new Object[size];
    }

    public T get(int index) {
        T rsl = null;
        int trueIndex = checkIndex(index, this.container.length);
        if (trueIndex < container.length && trueIndex >= 0) {
            rsl = (T) this.container[trueIndex];
        }
        return rsl;
    }

    public void add(T model) {
        if (container.length % 10 == 0) {
           new SimpleArray<T>(10 + modCount);
      }
        this.container[modCount++] = model;
    }

    @Override
    public Iterator<T> iterator() throws ConcurrentModificationException {
        int expectedModCount = modCount;
        return (Iterator<T>) new Iterator<Objects>() {

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return modCount < container.length;
            }

            @Override
            public Objects next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (Objects) container[modCount++];
            }
        };
    }
}
