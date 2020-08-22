package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTestTemporaryFolder {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testAnalizy() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        List<String> sourceList = new ArrayList<String>();
        try (PrintWriter out = new PrintWriter(source)) {
            out.print("500 10:55:01\n" + "\n" + "200 10:56:01\n" + "\n" + "500 10:57:01\n" + "\n" + "400 10:58:01\n" + "\n" + "400 10:58:02\n" + "\n" + "200 10:59:01\n" + "\n" + "500 11:01:02\n" + "\n" + "400 11:01:10\n" + "\n" + "200 11:02:02\n" + "\n" + "200 11:02:10\n" + "\n" + "500 11:03:15\n" + "\n" + "200 11:04:10 ");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            sourceList = in.lines().collect(Collectors.toList());
        }
        assertThat(sourceList.get(0), is("10:55:01;10:56:01"));
        assertThat(sourceList.get(1), is("10:57:01;10:59:01"));
        assertThat(sourceList.get(2), is("11:01:02;11:02:02"));
        assertThat(sourceList.get(3), is("11:03:15;11:04:10"));
    }
}
