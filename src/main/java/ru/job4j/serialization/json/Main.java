package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Создаём новую json-строку с модифицированными данными*/
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        /* Преобразуем объект Employee в JSON */
        final Employee john = new Employee(true, 200000L, "John",
                new Department("IT", "Moscow"), new int[]{404, 500});

        String jsonJohn = gson.toJson(john);
        System.out.println(jsonJohn);

        /* Повышаем зарплату Джону */
        double newSalary = 250000.0;

        Pattern pattern = Pattern.compile("\"salary\"\\s*:\\s*(\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(jsonJohn);

        if (matcher.find()) {
            jsonJohn = matcher.replaceAll("\"salary\": " + newSalary);
        }

        /* Создаем нового Джона */
        final Employee happyJohn = gson.fromJson(jsonJohn, Employee.class);
        System.out.println(happyJohn);
    }
}