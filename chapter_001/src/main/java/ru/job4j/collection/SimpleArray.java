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
    private int position = 0;

    public SimpleArray(int size) {
        this.container = new Object[size];
    }

    public T get(int index) {
       return (T) container[checkIndex(index, position)];
    }

    public void add(T model) {
        this.modCount++;
        this.container[position++] = model;
    }

    @Override
    public Iterator<T> iterator() throws ConcurrentModificationException {
        int expectedModCount = modCount;
        return (Iterator<T>) new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return position < container.length;
            }

            @Override
            public T next() {
                if (expectedModCount != position) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[position++];
            }
        };
    }
}
