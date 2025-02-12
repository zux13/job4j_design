package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    public void testMergeSortEmptyArray() {
        int[] array = {};
        int[] expected = {};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortSingleElementArray() {
        int[] array = {5};
        int[] expected = {5};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortTwoElementsArrayAlreadySorted() {
        int[] array = {1, 2};
        int[] expected = {1, 2};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortTwoElementsArrayNotSorted() {
        int[] array = {2, 1};
        int[] expected = {1, 2};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortUnsortedArray() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortArrayWithDuplicates() {
        int[] array = {4, 2, 2, 8, 3, 3, 1};
        int[] expected = {1, 2, 2, 3, 3, 4, 8};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortArrayWithNegativeNumbers() {
        int[] array = {-3, -1, 2, -5, 0};
        int[] expected = {-5, -3, -1, 0, 2};
        assertArrayEquals(expected, Merge.mergesort(array));
    }

    @Test
    public void testMergeSortLargeArray() {
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = 1000 - i;
        }
        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            expected[i] = i + 1;
        }
        assertArrayEquals(expected, Merge.mergesort(array));
    }
}