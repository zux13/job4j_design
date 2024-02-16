package ru.job4j.generics;

public class Role extends User {

    private final String role;

    public Role(String id, String name, String role) {
        super(id, name);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
