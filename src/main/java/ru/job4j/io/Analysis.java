package ru.job4j.io;

import java.io.*;

public class Analysis {

    public boolean isAvailable;

    {
        isAvailable = true;
    }

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            reader.lines()
                    .filter(s -> isStartOfIdle(s) || isEndOfIdle(s))
                    .map(s -> s.substring(4) + ";" + (!isAvailable ? "" : System.lineSeparator()))
                    .forEach(s -> {
                        try {
                            writer.write(s);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isStartOfIdle(String line) {
        boolean rsl = isAvailable && (line.startsWith("400") || line.startsWith("500"));
        if (rsl) {
            isAvailable = false;
        }
        return rsl;
    }

    public boolean isEndOfIdle(String line) {
        boolean rsl = !isAvailable && (line.startsWith("200") || line.startsWith("300"));
        if (rsl) {
            isAvailable = true;
        }
        return rsl;
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}