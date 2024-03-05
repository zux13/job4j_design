package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
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
