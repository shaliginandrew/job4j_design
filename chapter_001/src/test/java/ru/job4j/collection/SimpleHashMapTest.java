package ru.job4j.collection;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import ru.job4j.collection.SimpleHashMap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



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
       Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(3));
        assertFalse(it.hasNext());

     //   assertThat(current.getValue(), is(map.get(2)));
      //  assertThat(iterator.hasNext(), is(true));
     //   assertThat(iterator.next(), is("в"));
     //  assertThat(iterator.hasNext(), is(false));
      //  assertThat(iterator.next(), is(null));


    }

}





