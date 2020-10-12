package ru.job4j.io;

import java.nio.file.Paths;

public class ArgsSearchFilesByCondition {

    private final String[] args;

    public ArgsSearchFilesByCondition(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return Paths.get(args[1]).toFile().isDirectory();
    }

    public String directory() {


        return args[1];
    }

    public String name() {
        return args[3];
    }

    public String output() {
        return args[6];
    }

    public String mode() {
        return args[4];
    }
}



