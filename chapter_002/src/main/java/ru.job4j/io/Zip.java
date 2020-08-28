package ru.job4j.io;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("Root folder, etx or outfile not set.");
        }
        if (argZip.valid()) {
            Zip zip = new Zip();
            List<Path> paths = Search.search(Paths.get(argZip.directory()), argZip.exclude());
            List<File> sources = new ArrayList<>();
            for (Path file : paths) {
                sources.add(file.toFile());
            }
            zip.packFiles(sources, new File(argZip.output()));
             }
               if (!argZip.valid()) {
                   System.out.println("Directory for Zip not exist");
               }
        }
    }
