package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class StringGeneratorTest {

    @Test
    public void whenCorrectTemplateAndArgumentsThenGenerateString() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("I am a Petr Arsentev, Who are you?");
    }

    @Test
    public void whenMissingKeyInArgsThenThrowException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing key in arguments: subject");
    }

    @Test
    public void whenExtraKeyInArgsThenThrowException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "extra", "value"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Extra keys in arguments: extra");
    }

    @Test
    public void whenTemplateIsEmptyThenReturnEmptyString() {
        Generator generator = new StringGenerator();
        String template = "";
        Map<String, String> args = Map.of();
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("");
    }

    @Test
    public void whenNoPlaceholdersInTemplateThenReturnSameString() {
        Generator generator = new StringGenerator();
        String template = "Hello, World!";
        Map<String, String> args = Map.of();
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("Hello, World!");
    }

    @Test
    public void whenPlaceholderWithoutClosingBraceThenThrowException() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid template format: missing closing brace");
    }
}
