package ru.job4j.cache.menu;

import ru.job4j.cache.CacheException;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    private static DirFileCache cache;
    private static final String MENU = """
            
            1. Указать кэшируемую директорию
            2. Загрузить содержимое файла в кэш
            3. Получить содержимое файла из кэша
            4. Выход
            
            Выберите опцию:
            """;
    private static final String PATH_TO_CACHE = "Введите путь к кэшируемой директории: ";
    private static final String FILE_NAME_TO = "Введите имя файла для загрузки в кэш: ";
    private static final String FILE_NAME_FROM = "Введите имя файла для получения из кэша: ";
    private static final String FILE_LOADED = "Файл загружен в кэш.";
    private static final String CHOOSE_DIRECTORY_FIRST = "Сначала укажите кэшируемую директорию.";
    private static final String INVALID_INPUT = "Неверный выбор, попробуйте снова.";

    private static void handleSetDirectory(Scanner scanner) {
        System.out.print(PATH_TO_CACHE);
        String dir = scanner.nextLine();
        cache = new DirFileCache(dir);
        System.out.printf("Текущая кэшируемая директория: %s\n", dir);
    }

    private static void handleLoadFile(Scanner scanner) {
        System.out.print(FILE_NAME_TO);
        String fileToLoad = scanner.nextLine();
        if (cache != null) {
            cache.get(fileToLoad);
            System.out.println(FILE_LOADED);
        } else {
            System.out.println(CHOOSE_DIRECTORY_FIRST);
        }
    }

    private static void handleGetFile(Scanner scanner) {
        System.out.print(FILE_NAME_FROM);
        String fileToGet = scanner.nextLine();
        if (cache != null) {
            String content = cache.get(fileToGet);
            System.out.printf("Содержимое файла: %s\n", content);
        } else {
            System.out.println(CHOOSE_DIRECTORY_FIRST);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.print(MENU);
                if (!scanner.hasNextInt()) {
                    throw new RuntimeException("Введите число");
                }
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleSetDirectory(scanner);
                        break;
                    case 2:
                        handleLoadFile(scanner);
                        break;
                    case 3:
                        handleGetFile(scanner);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println(INVALID_INPUT);
                }
            } catch (CacheException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.printf("Ошибка: %s\n", e.getMessage());
                scanner.nextLine();
            }
        }
    }
}