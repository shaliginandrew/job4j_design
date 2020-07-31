package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class AnalizeTest {
        @Test
        public void whenAddisOneTest() {
            Analize analize = new Analize();
            Analize.User item1 = new Analize.User(1, "Андрей");
            Analize.User item2 = new Analize.User(2, "Сергей");
            Analize.User item3 = new Analize.User(3, "Иван");
            List<Analize.User>  prev = List.of(item1, item2);
            List<Analize.User>  current = List.of(item1, item2, item3);
           assertThat(analize.diff(prev, current).added,  is(1));
           assertThat(analize.diff(prev, current).deleted,  is(0));
           assertThat(analize.diff(prev, current).changed,  is(0));
        }



        @Test
        public void whenDeleteTest() {
            Analize analize = new Analize();
            Analize.User item1 = new Analize.User(1, "Андрей");
            Analize.User item2 = new Analize.User(2, "Сергей");
            Analize.User item3 = new Analize.User(3, "Иван");
            List<Analize.User>  prev = List.of(item1, item2, item3);
            List<Analize.User>  current = List.of(item1, item2);
            assertThat(analize.diff(prev, current).deleted, is(1));
            assertThat(analize.diff(prev, current).added, is(0));
            assertThat(analize.diff(prev, current).changed, is(0));
        }


        @Test
        public void whenChangeTest() {
            Analize analize = new Analize();
            Analize.User item1 = new Analize.User(1, "Андрей");
            Analize.User item2 = new Analize.User(2, "Сергей");
            Analize.User item3 = new Analize.User(3, "Иван");
            Analize.User item4 = new Analize.User(3, "Петр");
            List<Analize.User>  prev = List.of(item1, item2, item3);
            List<Analize.User>  current = List.of(item1, item2, item4);
            assertThat(analize.diff(prev, current).changed, is(1));
            assertThat(analize.diff(prev, current).deleted, is(0));
            assertThat(analize.diff(prev, current).added, is(0));
        }
}

