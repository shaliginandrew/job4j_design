package ru.job4j.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    @Test
    public void testStandardMapOne() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        map.insert("Lars", 1);
        map.insert("Lars", 2);
        map.insert("Lars", 1);
        assertThat(map.get("Lars"), is(1));
    }

    @Test
    public void testStandardMapTwo() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        map.insert("Lars", 1);
        map.insert("Bars", 4);
        map.insert("Dog",  0);
        assertThat(map.get("Bars"), is(4));
    }

    @Test
    public void testSize() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        for (int i = 0; i < 100; i++) {
            map.insert(String.valueOf(i), i);
        }
        assertThat(map.size(), is(100));
    }

    @Test
    public void testSizeGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        for (int i = 0; i < 100; i++) {
            map.insert(String.valueOf(i), i);
        }
        assertThat(map.get("51"), is(51));
    }


    @Test
    public void delete() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        for (int i = 0; i < 100; i++) {
            map.insert(String.valueOf(i), i);
        }
        map.delete("51");
        assertThat(null, is(map.get("51")));
    }
}
