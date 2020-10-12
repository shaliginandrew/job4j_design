package ru.job4j.io;

import java.nio.file.Paths;

public class Args {

    private static final int DIR_KEY = 0;
    private static final int PATTERN_KEY = 2;
    private static final int SEARCH_TYPE = 4;
    private static final int LOG_PATH_KEY = 5;
    private final String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    public void validate() {

        if (args.length != 7) {
            throw new IllegalArgumentException("Не все аргументы заданы");
        }
        if (!Paths.get(args[DIR_KEY + 1]).toFile().isDirectory()) {
           System.out.println("Не верно указана директория");
        }
        if (!args[DIR_KEY].startsWith("-")
                || !args[PATTERN_KEY].startsWith("-")
                || !args[SEARCH_TYPE].startsWith("-")
                || !args[LOG_PATH_KEY].startsWith("-")) {
            throw new IllegalArgumentException("Args must be started with -");
        }
        if (!args[DIR_KEY].equalsIgnoreCase("-d")
                || !args[PATTERN_KEY].equalsIgnoreCase("-n")
                || !args[LOG_PATH_KEY].equalsIgnoreCase("-o")) {
            throw new IllegalArgumentException("Wrong arg name!");
        }
    }

    public String directory() {
        return args[DIR_KEY + 1];
    }

    public String name() {
        return args[PATTERN_KEY + 1];
    }

    public String output() {
        return args[LOG_PATH_KEY + 1];
    }

    public String mode() {
        return args[SEARCH_TYPE];
    }
}



