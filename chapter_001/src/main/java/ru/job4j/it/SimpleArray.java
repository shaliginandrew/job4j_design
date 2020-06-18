package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.*;

/**
 * Класс - универсальная обертка над массивом
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
        this.index = index;
    }

    /**
     * Добавляет указанный элемент (model) в первую свободную ячейку
     * @param model
     */
    public void add(T model) {
        this.objects[index++] = model;
    }

    /**
     * Заменяет указанным элементом (model) элемент, находящийся по индексу index
     * @param index
     * @param model
     */
    public void set(int index, T model) {
        int trueIndex = checkIndex(index, this.objects.length);
        this.objects[trueIndex] = model;
    }

    /**
     *  удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево
     *  (в середине массива не должно быть пустых ячеек);
     * @param index
     */
    public void remove(int index) {

            if (Objects.checkIndex(index, objects.length) < objects.length) {
                Object[] objectsDest = new Object[objects.length - 1];
                System.arraycopy(this.objects, 0, objectsDest, 0, index);
                System.arraycopy(this.objects, index + 1, objectsDest, index, this.objects.length - index - 1);
                this.index--;
            }
    }
    /**
     * Возвращает элемент, расположенный по указанному индексу;
     * @param index
     */
    public T get(int index) {
          int trueIndex = checkIndex(index, this.objects.length);
        return (T) this.objects[trueIndex];
    }


    @Override
    public Iterator<T> iterator() {

            return (Iterator<T>) new Iterator<Objects>() {

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public boolean hasNext() {
                    return index < objects.length;
                }

                @Override
                public Objects next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return (Objects) objects[index++];
                }
            };
        }
}



