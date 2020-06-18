package ru.job4j.generic;

public abstract class Base {
    private String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}