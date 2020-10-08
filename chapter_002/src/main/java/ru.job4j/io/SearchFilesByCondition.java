package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFilesByCondition {

    public static void main(String[] args) throws IOException {
        ArgsSearchFilesByCondition argsSearchFilesByCondition = new ArgsSearchFilesByCondition(args);
        if (args.length != 7) {
            throw new IllegalArgumentException("Не все аргументы заданы");
        }
        if (!argsSearchFilesByCondition.valid()) {
            System.out.println("Не верно указана директория");
        }


        Path folder = Paths.get(argsSearchFilesByCondition.directory());


        if (argsSearchFilesByCondition.mode().equals("-f")) {
            Predicate<Path> conditionF = p -> p.toFile().getName().equals(argsSearchFilesByCondition.name());

            Files.walk(folder)
                    .filter(conditionF).forEach(System.out::println);
        }

        if (argsSearchFilesByCondition.mode().equals("-m")) {
            Predicate<Path> conditionM = p -> p.toFile().getName().endsWith(argsSearchFilesByCondition.name());

            Files.walk(folder)
                    .filter(conditionM).forEach(System.out::println);
        }

        if (argsSearchFilesByCondition.mode().equals("-r")) {
            Pattern pat = Pattern.compile(argsSearchFilesByCondition.name());


            Predicate<Path> conditionR = path -> {
               boolean result = false;
               Matcher mat = pat.matcher(path.toFile().getName());
               if (path.toFile().isFile() && mat.find()) {
                  result = true;
               }
               return result;
           };


           Files.walk(folder)
                    .filter(conditionR).forEach(System.out::println);
        }


    }
}
