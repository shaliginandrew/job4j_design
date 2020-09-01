package ru.job4j.io;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String log4jConfPath = "C:/Projects/job4j_design/chapter_002/src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        String name = "Petr Arsentev";
        int age = 33;
        float one = (float) 10.2;
        double two = 10.254;
        char three = 'd';
        byte four = 10;
        short five = 12;
        long six = 1021564;
        boolean seven = true;
        int eight = 25;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Primitive type float: {} ", one);
        LOG.debug("Primitive type double: {} ", two);
        LOG.debug("Primitive type char: {} ", three);
        LOG.debug("Primitive type byte: {} ", four);
        LOG.debug("Primitive type short: {} ", five);
        LOG.debug("Primitive type long: {} ", six);
        LOG.debug("Primitive type boolean: {} ", seven);
        LOG.debug("Primitive type int: {} ", eight);

    }
}