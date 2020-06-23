package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class  SimpleListTest {
    @Test
    public void getIndex() {
        SimpleList<Integer> node = new SimpleList(1);
        node.add(2);
        node.add(3);
        node.add(4);
        int rsl = node.get(2);
        assertThat(rsl, is(3));
    }

    @Test
    public void whenAddThenIt() {
        SimpleList<String> node = new SimpleList("first");
        String rsl = node.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleList<String> node = new SimpleList("first");
        Iterator<String> it = node.iterator();
        node.add("second");
        it.next();
    }
}

