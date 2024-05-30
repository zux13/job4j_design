package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(attributes.size(), file.getFileName().toString());
        files.computeIfAbsent(property, k -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        files.forEach((key, paths) -> {
            if (paths.size() > 1) {
                System.out.println("Duplicate files:");
                paths.forEach(System.out::println);
            }
        });
    }
}