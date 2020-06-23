package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class  SimpleListTest {
    @Test
    public void getIndex() {
        SimpleList node = new SimpleList(1);
        node.add(2);
        node.add(3);
        node.add(4);
        int rsl = (int) node.get(2);
        assertThat(rsl, is(3));
    }
    @Test
    public void whenAddThenIt() {
        SimpleList node = new SimpleList(1);
        node.add(2);
        int rsl = (int) node.iterator().next();
        assertThat(rsl, is(2));
    }
}

