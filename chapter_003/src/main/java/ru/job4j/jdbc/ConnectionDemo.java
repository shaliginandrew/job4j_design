package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("C:/Projects/job4j_design/chapter_003/src/main/resources/app.properties"))) {
            in.lines().forEach(e -> lines.add(e.split("=")[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = lines.get(0);
        String login = lines.get(1);
        String password = lines.get(2);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}