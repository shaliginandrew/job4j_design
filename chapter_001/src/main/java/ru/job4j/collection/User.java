package ru.job4j.collection;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private Set<String> emails;

    public User(String name, Set<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emails=" + emails +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }
}


