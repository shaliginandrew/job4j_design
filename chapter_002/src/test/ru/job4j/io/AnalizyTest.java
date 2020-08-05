package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void testOne() {
        List<String> sourceList = new ArrayList<String>();
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("server.log")))) {
            out.print("500 10:55:01\n" + "\n" + "200 10:56:01\n" + "\n" + "500 10:57:01\n" + "\n" + "400 10:58:01\n" + "\n" + "400 10:58:02\n" + "\n" + "200 10:59:01\n" + "\n" + "500 11:01:02\n" + "\n" + "400 11:01:10\n" + "\n" + "200 11:02:02\n" + "\n" + "200 11:02:10\n" + "\n" + "500 11:03:15\n" + "\n" + "200 11:04:10 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        analizy.unavailable("server.log", "unavailable.csv");

        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.csv"))) {
            sourceList = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();

        }
        assertThat(sourceList.get(0), is("10:55:01;10:56:01"));
        assertThat(sourceList.get(1), is("10:57:01;10:59:01"));
        assertThat(sourceList.get(2), is("11:01:02;11:02:02"));
        assertThat(sourceList.get(3), is("11:03:15;11:04:10"));
    }
}

