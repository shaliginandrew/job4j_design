package ru.job4j.io;

import org.junit.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void testConfigOne() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
    }

    @Test
    public void testConfigTwo() {
        String path = "./chapter_002/data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }
}