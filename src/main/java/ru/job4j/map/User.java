package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        User usr1 = new User("Rustem", 100, birthday);
        User usr2 = new User("Rustem", 100, birthday);
        map.put(usr1, new Object());
        map.put(usr2, new Object());
        for (User name: map.keySet()) {
            System.out.println(name + "=" + map.get(name));
        }
    }
}
