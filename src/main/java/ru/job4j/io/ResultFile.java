package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/result.txt")
                ))) {
            for (int i = 1; i <= 9; i++) {
                output.printf("1 * %d = %d%n", i, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}