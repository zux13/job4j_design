package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemovingByPredicate() {
        Predicate<Integer> predicate = i -> i >= 3;
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(2).containsSequence(1, 2);
    }

    @Test
    void whenReplacingByPredicate() {
        Predicate<Integer> predicate = i -> i >= 3;
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 56);
        ListUtils.replaceIf(input, predicate, -1);
        assertThat(input).hasSize(4).containsSequence(1, -1, 2, -1);
    }

    @Test
    void whenRemoveAllByCollection() {
        List<Integer> filter = List.of(1, 11, 5);
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeAll(input, filter);
        assertThat(input).hasSize(2).containsSequence(2, 3);
    }
}