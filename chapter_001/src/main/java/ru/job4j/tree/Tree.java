package ru.job4j.tree;

import ru.job4j.collection.SimpleList;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;
    private Node<E> parentNode;
    private Node<E> childNode;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        boolean parentIsPresent = findBy(parent).isPresent();
        boolean childIsPresent = findBy(child).isPresent();
        if (parentIsPresent && !childIsPresent) {
            parentNode = findBy(parent).get();
            childNode = new Node<>(child);
            parentNode.children.add(childNode);
            rsl = true;
        }

        return rsl;
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                return false;
            }
            data.addAll(el.children);
        }
        return true;
    }
/*
1. Создаем перменную пустую Node в обертке Optional
2. Создается очередь data из Node  реализации LinkedList
3. Добавляем элемент root в очередь( если нельзя добавить вернет false)
4. До тех пор пока очередь не пустая:
-сначала проверяем root, если не равно искомому - добавляем всех детей root в очередь
-root уже выйдет, буде проверять всех детей поочереди и дети поочереди будут выходить из нее
-если не находим совпадений, то добавляем всех детей тех элементов что выше перебирали и перебираем дальше,
 пока не найдем или очередь не закончится

 */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
