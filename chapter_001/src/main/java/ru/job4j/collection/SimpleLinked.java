package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

import static java.util.Objects.checkIndex;

/**
 * Необходимо создать динамический контейнер с методами:
 * 1) add(E value); (добавляет в конец)
 * 2) E get(int index);
 * 3) реализовать интерфейс Iterable<E>.
 * Внутри контейнер должен базироваться на связанном списке(Node<E> node). Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.)
 * запрещено. Контейнер должен быть динамическим, т.е. увеличиваться по мере добавления элементов.
 * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора коллекция подверглась структурному изменению,
 * итератор должен кидать ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно модифицирует коллекцию должна
 * инкрементировать этот счетчик. В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount),
 * а затем на каждой итерации сравнивает сохраненное значение, с текущим значением поля modCount, если они отличаются, то генерируется
 * исключение.
 * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов. Например, вы добавили
 * 3 элемента. Каким может быть индекс? [0, 2]. Для проверки индекса используйте метод Objects.checkIndex.
 */
public class SimpleLinked<E> implements Iterable<E> {
  private Node<E> node;
  private int modCount = 0;

    public E get(int index) {

    }

    public void add(E value) {
       node[]
    }
    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException {
        int expectedModCount = modCount;
        return (Iterator<E>) new Iterator<Objects>() {

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return modCount < node;
            }

            @Override
            public Objects next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (Objects) node[modCount++];
            }
        };
    }
}
