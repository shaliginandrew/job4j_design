package ru.job4j.io;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class FindbyPredicate {


    public List<Path> findByPredicate(Path root, String mode) {



        if (mode.equals("-m")) {


        }
        if (mode.equals("-r")) {


        }

        if (mode.equals("-f")) {


        }

        Predicate<Path> conditionMask = p -> p.toFile().getName().endsWith(mode);
        Predicate<Path> conditionRegular = ;
        Predicate<Path> conditionName = ;


        SearchFiles seacher = new SearchFiles(conditionMask, root.toString());

    }


}
