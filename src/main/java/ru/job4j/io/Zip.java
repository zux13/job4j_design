package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target, Path root) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                Path relativePath = root.relativize(source);
                zip.putNextEntry(new ZipEntry(relativePath.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName args) {

        Path directory = Paths.get(args.get("d"));
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("The '-d' argument must be a valid directory path");
        }

        String fileExtension = args.get("e");
        if (!fileExtension.startsWith(".") || fileExtension.length() == 1) {
            throw new IllegalArgumentException(
                    "The '-e' argument must be a valid file extension to exclude (e.g., .class)"
            );
        }

        String output = args.get("o");
        if (!output.toLowerCase().endsWith(".zip") || output.length() == 4) {
            throw new IllegalArgumentException("The '-o' argument must be a valid file name with '.zip' extension");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);

        Path root = Paths.get(argsName.get("d"));
        List<Path> sources = Search.search(root, path -> !path.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(argsName.get("o")), root);
    }
}