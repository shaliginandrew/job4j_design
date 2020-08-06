package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод должен загружать пару ключ-значение в карту values.
     * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
     */
    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().filter(line -> !line.contains("#") && line.length() > 0).forEach(line -> {
                String[] lineValue = line.split("=");
                values.put(lineValue[0], lineValue[1]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
       return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.password"));
    }
}