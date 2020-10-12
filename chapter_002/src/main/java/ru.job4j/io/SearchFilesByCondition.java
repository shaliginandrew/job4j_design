package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFilesByCondition {

    public static void main(String[] args) throws IOException {
        List<String> saveResult = new ArrayList<String>();
        ArgsSearchFilesByCondition argsSearchFilesByCondition = new ArgsSearchFilesByCondition(args);
        if (args.length != 7) {
            throw new IllegalArgumentException("Не все аргументы заданы");
        }
        if (!argsSearchFilesByCondition.valid()) {
            System.out.println("Не верно указана директория");
        }
        Path folder = Paths.get(argsSearchFilesByCondition.directory());
        if (argsSearchFilesByCondition.mode().equals("-f")) {
            Predicate<Path> condition = p -> p.toFile().getName().equals(argsSearchFilesByCondition.name());

            Files.walk(folder)
                    .filter(condition).forEach(path -> saveResult.add(String.valueOf(path)));
        }
        if (argsSearchFilesByCondition.mode().equals("-m")) {
            Predicate<Path> condition = p -> p.toFile().getName().endsWith(argsSearchFilesByCondition.name());

            Files.walk(folder)
                    .filter(condition).forEach(path -> saveResult.add(String.valueOf(path)));
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
                    .filter(conditionR).forEach(path -> saveResult.add(String.valueOf(path)));
        }
        try (PrintWriter out  = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsSearchFilesByCondition.output())))) {
            for (String list : saveResult) {
                out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
