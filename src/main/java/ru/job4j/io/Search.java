package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly two arguments are required: <start directory> <file extension>");
        }

        Path startPath = Paths.get(args[0]);
        if (!Files.exists(startPath) || !Files.isDirectory(startPath)) {
            throw new IllegalArgumentException("The first argument must be a valid directory path");
        }

        String fileExtension = args[1];
        if (!fileExtension.startsWith(".") || fileExtension.length() == 1) {
            throw new IllegalArgumentException("The second argument must be a valid file extension (e.g., .js)");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}