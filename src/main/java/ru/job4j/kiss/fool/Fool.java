package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    private static String correctAnswer(int startAt) {
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            return "FizzBuzz";
        }
        if (startAt % 3 == 0) {
            return "Fizz";
        }
        if (startAt % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(startAt);
    }

    private static boolean isCorrectAnswer(String answer, int startAt) {
        return correctAnswer(startAt).equals(answer);
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(correctAnswer(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!isCorrectAnswer(answer, startAt)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}
