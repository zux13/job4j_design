package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {

            String line = reader.readLine();
            String start = "";
            boolean serverDown = false;

            while (line != null) {

                if ((line.startsWith("400") || line.startsWith("500")) && !serverDown) {
                    start = line.substring(line.indexOf(" ") + 1);
                    serverDown = true;
                } else if ((line.startsWith("200") || line.startsWith("300")) && serverDown) {
                    writer.append(start)
                            .append(";")
                            .append(line.substring(line.indexOf(" ") + 1))
                            .append(";")
                            .append(System.lineSeparator());
                    serverDown = false;
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}