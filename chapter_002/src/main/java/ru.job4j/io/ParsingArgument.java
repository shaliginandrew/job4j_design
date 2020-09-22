package ru.job4j.io;

public class ParsingArgument {

    String[] argument;

    public ParsingArgument(String[] argument) {
        this.argument = argument;

    }

    public String directory() {
        return argument[1];

    }

    public String output() {

        return argument[6];

    }

    public String name() {
        return argument[3];
    }


    public String mode() {
        return argument[4];

    }

    public static void main(String[] args) {
        ParsingArgument parsingArgument = new ParsingArgument(args);


        for (String value : args) {
            System.out.println(value);
        }
    }
}
