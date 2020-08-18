package ru.job4j.collection;




import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class SimpleHashMapTest {



    @Test
    public void testIterator() {
       SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
       map.insert(1, "а");
       map.insert(2, "б");
       map.insert(3, "в");
       Iterator iterator = map.iterator();
       SimpleHashMap.Node<Integer, String> current = (SimpleHashMap.Node<Integer, String>) iterator.next();
       assertThat(iterator.hasNext(), is(true));
       assertThat(current.getValue(), is(map.get(1)));
       assertThat(iterator.hasNext(), is(true));
        assertThat(current.getValue(), is(map.get(2)));
      //  assertThat(iterator.hasNext(), is(true));
     //   assertThat(iterator.next(), is("в"));
     //  assertThat(iterator.hasNext(), is(false));
      //  assertThat(iterator.next(), is(null));


    }

}





