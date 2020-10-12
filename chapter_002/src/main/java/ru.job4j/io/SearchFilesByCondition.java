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

    List<String> saveResult = new ArrayList<String>();

    public Predicate<Path> newCondition(Args argsWrapper) throws IOException {
        Predicate<Path> condition = p -> true;
        if (argsWrapper.mode().equals("-f")) {
            condition = p -> p.toFile().getName().equals(argsWrapper.name());
        }
        if (argsWrapper.mode().equals("-m")) {
            condition = p -> p.toFile().getName().endsWith(argsWrapper.name());
        }
        if (argsWrapper.mode().equals("-r")) {
            Pattern pat = Pattern.compile(argsWrapper.name());
            condition = path -> {
                boolean result = false;
                Matcher mat = pat.matcher(path.toFile().getName());
                if (path.toFile().isFile() && mat.find()) {
                    result = true;
                }
                return result;
            };
        }
        return condition;
    }

    public void writeLog(Args args) {
        try (PrintWriter out  = new PrintWriter(new BufferedOutputStream(new FileOutputStream(args.output())))) {
            for (String list : saveResult) {
                out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void search(Args args, Predicate<Path> condition) throws IOException {
        Path folder = Paths.get(args.directory());
        Files.walk(folder)
                .filter(condition).forEach(path -> saveResult.add(String.valueOf(path)));
    }

    public static void main(String[] args) throws IOException {
        Args argsWrapper = new Args(args);
        argsWrapper.validate();
        SearchFilesByCondition sf = new SearchFilesByCondition();
        Predicate<Path> condition = sf.newCondition(argsWrapper);
        sf.search(argsWrapper, condition);
        sf.writeLog(argsWrapper);
    }
}
