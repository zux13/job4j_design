package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        try (Scanner scanner = new Scanner(new File(path))) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("The CSV file is empty.");
            }

            boolean isHeader = true;
            List<Integer> indices = new ArrayList<>();
            List<String> outputLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(delimiter);
                StringJoiner sj = new StringJoiner(delimiter);
                if (isHeader) {
                    List<String> header = Arrays.asList(tokens);
                    for (String f : filter.split(",")) {
                        if (header.contains(f)) {
                            indices.add(header.indexOf(f));
                            sj.add(f);
                        }
                    }
                    isHeader = false;
                } else {
                   for (int i : indices) {
                       sj.add(tokens[i]);
                   }
                }
                outputLines.add(sj.toString());
            }

            if ("stdout".equals(out)) {
                outputLines.forEach(System.out::println);
            } else {
                Path outPath = Paths.get(out);
                Files.write(outPath, outputLines);
            }
        }
    }

    private static void validateArgs(ArgsName args) {
        String path = args.get("path");
        if (!path.toLowerCase().endsWith(".csv") || path.length() == 4) {
            throw new IllegalArgumentException("The '-path' argument must be a valid file name with '.csv' extension");
        }
        String out = args.get("out");
        Path outPath = Paths.get(out);
        if (!"stdout".equals(out) && (!outPath.toFile().exists() || !outPath.toFile().isFile())) {
            throw new IllegalArgumentException(
                    "The '-out' argument must provide either 'stdout' or a path to an existing file"
            );
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }
}