package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairsWithComments() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenOnlyCommentsAndEmptyLines() {
        String path = "./data/comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#key")).isNull();
        assertThat(config.value("key")).isNull();
    }

    @Test
    void whenWrongFileFormat() {
        String path = "./data/wrong_format.properties";
        Config config = new Config(path);
        Throwable thrown = assertThrows(IllegalArgumentException.class, config::load);
        assertThat(thrown.getMessage()).contains("empty key or value");
    }
}