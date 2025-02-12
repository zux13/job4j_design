package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickListTest {
    @Test
    void whenSortThenOk() {
        User user1 = new User(1, "11");
        User user2 = new User(1, "22");
        User user3 = new User(3, "33");
        User user4 = new User(-4, "44");
        User user5 = new User(4, "55");
        User user6 = new User(6, "66");
        Comparator<User> comparator = Comparator.comparingInt(User::getId);
        List<User> listUser = new ArrayList<>();
        listUser.add(user3);
        listUser.add(user5);
        listUser.add(user1);
        listUser.add(user4);
        listUser.add(user6);
        listUser.add(user2);
        QuickList.quickSort(listUser, comparator);
        List<Integer> result = listUser.stream().map(User::getId).toList();
        assertThat(result).containsExactly(-4, 1, 1, 3, 4, 6);
    }

    @Test
    void whenReverseOrderThenOk() {
        Comparator<Integer> comparator = (a, b) -> b - a;
        List<Integer> list = new ArrayList<>();
        list.add(-2);
        list.add(-8);
        list.add(1);
        list.add(0);
        list.add(10);
        list.add(1);
        list.add(8);
        QuickList.quickSort(list, comparator);
        System.out.println(list);
        assertThat(list).containsExactly(10, 8, 1, 1, 0, -2, -8);
    }

    @Test
    public void testQuickSortEmptyList() {
        List<Integer> list = new ArrayList<>();
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(0, list.size());
    }

    @Test
    public void testQuickSortSingleElementList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5));
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(Arrays.asList(5), list);
    }

    @Test
    public void testQuickSortAlreadySorted() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testQuickSortReverseSorted() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testQuickSortUnsorted() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5));
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(Arrays.asList(1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9), list);
    }

    @Test
    public void testQuickSortWithDuplicates() {
        List<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 2, 8, 3, 3, 1));
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(Arrays.asList(1, 2, 2, 3, 3, 4, 8), list);
    }

    @Test
    public void testQuickSortWithNegativeNumbers() {
        List<Integer> list = new ArrayList<>(Arrays.asList(-3, -1, 2, -5, 0));
        QuickList.quickSort(list, Comparator.naturalOrder());
        assertEquals(Arrays.asList(-5, -3, -1, 0, 2), list);
    }

    @Test
    public void testQuickSortLargeList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(1000 - i);
        }
        QuickList.quickSort(list, Comparator.naturalOrder());
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            expected.add(i + 1);
        }
        assertEquals(expected, list);
    }

    @Test
    public void testQuickSortCustomComparator() {
        List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "orange", "grape"));
        QuickList.quickSort(list, Comparator.reverseOrder());
        assertEquals(Arrays.asList("orange", "grape", "banana", "apple"), list);
    }

    private static class User {
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }
    }
}