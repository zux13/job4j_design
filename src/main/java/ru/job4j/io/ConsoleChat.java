package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean active = true;
        boolean stoped = false;

        System.out.println("Начало чата. Введите сообщение (или 'закончить' для завершения):");

        while (active) {
            String userInput = scanner.nextLine();
            log.add("User: " + userInput);

            if (OUT.equals(userInput)) {
                active = false;
            } else if (STOP.equals(userInput)) {
                stoped = true;
            } else if (CONTINUE.equals(userInput)) {
                stoped = false;
            } else if (!stoped) {
                String botAnswer = botPhrases.get(random.nextInt(botPhrases.size()));
                System.out.println(botAnswer);
                log.add("Bot: " + botAnswer);
            }
        }

        saveLog(log);
        System.out.println("Чат завершен. Лог сохранен в файл: " + path);

    }

    private List<String> readPhrases() {
        try {
            return Files.readAllLines(Paths.get(botAnswers));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/botAnswers.log", "data/botAnswers.txt");
        consoleChat.run();
    }
}