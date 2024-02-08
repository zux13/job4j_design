package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "fourth", "fifth", "sixth");
        assertThat(list).hasSize(6)
                .element(2)
                .isNotNull()
                .isEqualTo("third");
        assertThat(list).isNotEmpty()
                .containsExactlyInAnyOrder("second", "third", "first", "sixth", "fourth", "fifth")
                .containsAnyOf("first", "bulochka", "chay")
                .containsSequence("third", "fourth")
                .doesNotContain("seventh")
                .endsWith("sixth");
        assertThat(list).filteredOn(s -> s.length() > 5)
                .containsExactlyInAnyOrder("second", "fourth")
                .size().isEqualTo(2);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "3", "5", "7", "9");
        assertThat(set).isNotNull()
                .allSatisfy(s -> {
                    assertThat(s.length()).isLessThan(2);
                    assertThat(s).doesNotContain("0");
                })
                .noneMatch(s -> s.equals("4"))
                .anyMatch(s -> s.equals("1"))
                .anySatisfy(s -> assertThat(s).endsWith("5"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("banana", "apple", "grapefruit", "pineapple");
        assertThat(map).isNotEmpty()
                .containsKeys("banana", "pineapple")
                .doesNotContainKey("watermelon")
                .containsValues(0, 3)
                .containsEntry("apple", 1)
                .doesNotContainValue(10);
    }
}