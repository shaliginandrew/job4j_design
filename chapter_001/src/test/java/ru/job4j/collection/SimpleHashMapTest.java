package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;



public class SimpleHashMapTest {

   @Test
    public void whenAddAndGet() {
       SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
       map.insert(1, "а");
       map.insert(2, "б");
       map.insert(3, "в");
       assertThat(map.get(1), is("а"));
       assertThat(map.getSize(), is(3));
   }

    @Test
    public void whenAddAndRemove() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "а");
        map.insert(2, "б");
        map.insert(3, "в");
        assertThat(map.delete(1), is(true));
        assertThat(map.getSize(), is(2));
    }

    @Test
    public void whenAddBySameKeyThenFalse() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "а");
        map.insert(3, "в");
        assertThat(map.insert(1, "б"), is(false));
    }

    @Test
    public void whenKeyNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(null, "1");
        assertThat(map.get(null), is("1"));
    }

    @Test
    public void whenDeleteByNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(null, "1");
        assertThat(map.delete(null), is(true));
        assertThat(map.getSize(), is(0));
    }

    @Test
    public void whenUpdateByNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(null, "1");
        assertThat(map.insert(null, "2"), is(false));
    }

    @Test
    public void testIterator() {
       SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
       map.insert(1, "а");
       map.insert(2, "б");
       map.insert(3, "в");
       Iterator<SimpleHashMap.Entry<Integer, String>> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(3));
        assertFalse(it.hasNext());

    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "1");
        Iterator<SimpleHashMap.Entry<Integer, String>> it = map.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "1");
        Iterator<SimpleHashMap.Entry<Integer, String>> it = map.iterator();
        it.next();
        map.insert(2, "2");
        it.next();
    }

    @Test
    public void whenKeyHashcodeNegative() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(-1, "-1");
        assertThat(map.get(-1), is("-1"));
    }
}





