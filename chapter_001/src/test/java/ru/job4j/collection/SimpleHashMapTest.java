package ru.job4j.collection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {
    @Test
    public void testAddLess16Elements() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "а");
        map.insert(2, "б");
        map.insert(3, "в");
        assertThat(map.iterator().next(), is("а"));
        assertThat(map.iterator().next(), is("б"));
        assertThat(map.iterator().next(), is("в"));
    }

    @Test
    public void testAddMore16Elements() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "а");
        map.insert(2, "б");
        map.insert(3, "в");
        map.insert(4, "г");
        map.insert(5, "д");
        map.insert(6, "е");
        map.insert(7, "ж");
        map.insert(8, "з");
        map.insert(9, "к");
        map.insert(10, "л");
        map.insert(11, "м");
        map.insert(12, "н");
        map.insert(13, "о");
        map.insert(14, "п");
        map.insert(15, "р");
        map.insert(16, "с");
        map.insert(17, "т");
    }
}
