package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person1 = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person1.isSex());
        jsonObject.put("age", person1.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person1).toString());

        /* Department & Employee */
        System.out.println();

        JSONObject jsonDepartment = new JSONObject("{\"departmentName\":\"Accounting\", \"location\":\"Ufa\"}");

        List<String> projectList = new ArrayList<>();
        list.add("2008");
        list.add("778");
        JSONArray jsonProjects = new JSONArray(projectList);

        final Employee employee = new Employee(false, 150000L, "Robert",
                new Department("Sales", "Spb"), new int[] {1407, 2000});
        JSONObject jsonEmp = new JSONObject();
        jsonEmp.put("fullTime", employee.isFullTime());
        jsonEmp.put("salary", employee.salary());
        jsonEmp.put("firstName", employee.firstName());
        jsonEmp.put("department", jsonDepartment);
        jsonEmp.put("projectIds", jsonProjects);

        System.out.println(jsonEmp);

        /* Автоматический разбор объектов типа Record не поддерживается конструктором JSONObject,
        В примере ниже получаем только одно разобранное поле, для корректной работы нужно преобразовать Record в Class
        либо использовать вариант с вызовом метода put() */
        System.out.println(new JSONObject(employee));
    }
}