package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class SimpleSetTest {

    @Test
    public void whenAdd() {
        SimpleSet<Integer> link = new SimpleSet<>(5);
        link.add(1);
        link.add(1);
        link.add(2);
        link.add(3);
        link.add(4);
        System.out.println(link.get(0));
    }
}


