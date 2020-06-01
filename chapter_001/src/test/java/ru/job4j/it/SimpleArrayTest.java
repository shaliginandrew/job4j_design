package ru.job4j.it;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    @Test
    public void testAddString() {
        SimpleArray<String> simple = new SimpleArray<String>(4);
        simple.add("test");
        String result = simple.get(0);
        assertThat(result, is("test"));
    }

    @Test
    public void testAddInt() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(4);
        simple.add(5);
        Integer result = simple.get(0);
        assertThat(result, is(5));
    }

    @Test
    public void testSet() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(4);
        simple.add(5);
        simple.add(6);
        simple.add(7);
        simple.set(1, 1);
        Integer result = simple.get(1);
        assertThat(result, is(1));
    }

    @Test
    public void testRemove() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(4);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.remove(1);
       // SimpleArray<Integer> result =
        //SimpleArray<Integer> expected =
        //assertThat(result, is(expected));
    }
}
