package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(s -> !s.startsWith("#") && !s.isEmpty())
                    .forEach(s -> {
                        int index = s.indexOf("=");
                        if (index == -1) {
                            throw new IllegalArgumentException("no '=' in line");
                        }
                        String key = s.substring(0, index);
                        String value = s.substring(index + 1);
                        if (key.isEmpty() || value.isEmpty()) {
                            throw new IllegalArgumentException("empty key or value");
                        }
                        values.put(key, value);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}