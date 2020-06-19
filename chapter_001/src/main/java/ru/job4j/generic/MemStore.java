package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Необходимо реализовать контейнеры для хранения объектов.
 * Структура контейнеров будет одинаковая. Все ограничения хранимых типов мы должны задать с помощью Generics.
 * Контейнеры должны быть описаны интерфейсом.
 * @param <T>
 */

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            mem.set(index, model); // как сохранять новый id?
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int findIndex = indexOf(id);
        if (findIndex != -1) {
            mem.remove(findIndex);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        return index != -1 ? mem.get(index) : null;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
}


