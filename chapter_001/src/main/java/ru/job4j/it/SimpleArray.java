package ru.job4j.it;

/**
 * Класс - универсальная обертка над массивом
 * @param <T>
 */
public class SimpleArray<T> {
    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
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
    this.objects[index] = model;
    }

    /**
     *  удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево
     *  (в середине массива не должно быть пустых ячеек);
     * @param index
     */
    public void remove(int index) {
        for (int i = index; i < objects.length; i++) {
            this.objects[i] = this.objects[i + 1];
        }
        this.objects[this.objects.length - 1] = null;
    }
    /**
     * Возвращает элемент, расположенный по указанному индексу;
     * @param index
     */
    public T get(int index) {
        return (T) this.objects[index];
    }
}
