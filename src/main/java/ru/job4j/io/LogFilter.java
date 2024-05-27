package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = Collections.emptyList();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            result = input.lines().filter(s -> s.substring(s.lastIndexOf(" ") - 3, s.lastIndexOf(" "))
                    .contains("404"))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
