package ru.job4j.io;

import java.nio.file.Paths;
import java.util.Arrays;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }


    public boolean valid() {
        return Paths.get(args[0]).toFile().isDirectory();
    }

    public String directory() {
        return args[0];
    }

    public String exclude() {
        return args[1];
    }

    public String output() {
        return args[2];
    }
}