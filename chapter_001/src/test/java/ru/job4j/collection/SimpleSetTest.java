package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.Iterator;

public class SimpleSetTest {

    @Test
    public void whenAdd() {
        SimpleSet<Integer> link = new SimpleSet<Integer>(6);
        link.add(1);
        link.add(2);
        link.add(1);
        link.add(2);
        link.add(3);
        link.add(0);
       for (Integer rsl : link) {
           System.out.println(rsl);
    }
    }
}


