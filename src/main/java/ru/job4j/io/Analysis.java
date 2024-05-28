package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            List<String> lines = reader.lines().toList();
            boolean isAvailable = true;
            for (String line : lines) {
                if (lineWithErrorStatus(line) && isAvailable) {
                    writer.write(line, 4, line.length() - 4);
                    writer.write(";");
                    isAvailable = false;
                } else if (!lineWithErrorStatus(line) && !isAvailable) {
                    writer.write(line, 4, line.length() - 4);
                    writer.write(";");
                    writer.newLine();
                    isAvailable = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean lineWithErrorStatus(String line) {
        return line.startsWith("400") || line.startsWith("500");
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}