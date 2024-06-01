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
                String[] parts = line.split(" ");
                String status = parts[0];
                String time = parts[1];

                if (("400".equals(status) || "500".equals(status)) && !serverDown) {
                    start = time;
                    serverDown = true;
                } else if (("200".equals(status) || "300".equals(status)) && serverDown) {
                    writer.append(start)
                            .append(";")
                            .append(time)
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