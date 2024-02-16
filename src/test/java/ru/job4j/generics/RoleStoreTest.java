package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
        assertThat(result.getRole()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        store.add(new Role("1", "Maxim", "User"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
        assertThat(result.getRole()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        store.replace("1", new Role("1", "Maxim", "User"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
        assertThat(result.getRole()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        store.replace("10", new Role("10", "Maxim", "User"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
        assertThat(result.getId()).isEqualTo("1");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        boolean result = store.replace("1", new Role("1", "Maxim", "User"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Admin"));
        boolean result = store.replace("10", new Role("10", "Maxim", "User"));
        assertThat(result).isFalse();
    }
}