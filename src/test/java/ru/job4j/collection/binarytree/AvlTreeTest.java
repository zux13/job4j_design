package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {

    @Test
    void symmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenInsertElementsThenTreeContainsThem() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertTrue(tree.insert(30));
        assertTrue(tree.insert(20));
        assertTrue(tree.insert(40));
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(25));
        assertTrue(tree.insert(35));
        assertTrue(tree.insert(50));

        assertTrue(tree.contains(30));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(40));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(25));
        assertTrue(tree.contains(35));
        assertTrue(tree.contains(50));

        assertFalse(tree.contains(5));
        assertFalse(tree.contains(100));
    }

    @Test
    void whenInsertDuplicateThenFalse() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertTrue(tree.insert(30));
        assertFalse(tree.insert(30));
    }

    @Test
    void whenRemoveElementThenTreeDoesNotContainIt() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);

        assertTrue(tree.remove(20));
        assertFalse(tree.contains(20));

        assertTrue(tree.remove(10));
        assertFalse(tree.contains(10));

        assertFalse(tree.remove(100));
    }

    @Test
    void whenMinimumAndMaximumThenCorrect() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(50);

        assertEquals(10, tree.minimum());
        assertEquals(50, tree.maximum());
    }

    @Test
    void whenTraversalInOrderThenSortedList() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(50);

        List<Integer> expected = List.of(10, 20, 25, 30, 35, 40, 50);
        assertEquals(expected, tree.inSymmetricalOrder());
    }

    @Test
    void whenTraversalPreOrderThenCorrectList() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(50);

        List<Integer> expected = List.of(30, 20, 10, 25, 40, 35, 50);
        assertEquals(expected, tree.inPreOrder());
    }

    @Test
    void whenTraversalPostOrderThenCorrectList() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(50);

        List<Integer> expected = List.of(10, 25, 20, 35, 50, 40, 30);
        assertEquals(expected, tree.inPostOrder());
    }
}