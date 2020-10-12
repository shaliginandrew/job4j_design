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
        Args argsWrapper = new Args(args);
        argsWrapper.validate();

        Path folder = Paths.get(argsWrapper.directory());
        if (argsWrapper.mode().equals("-f")) {
            Predicate<Path> condition = p -> p.toFile().getName().equals(argsWrapper.name());

            Files.walk(folder)
                    .filter(condition).forEach(path -> saveResult.add(String.valueOf(path)));
        }
        if (argsWrapper.mode().equals("-m")) {
            Predicate<Path> condition = p -> p.toFile().getName().endsWith(argsWrapper.name());

            Files.walk(folder)
                    .filter(condition).forEach(path -> saveResult.add(String.valueOf(path)));
        }
        if (argsWrapper.mode().equals("-r")) {
            Pattern pat = Pattern.compile(argsWrapper.name());
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
        try (PrintWriter out  = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsWrapper.output())))) {
            for (String list : saveResult) {
                out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
