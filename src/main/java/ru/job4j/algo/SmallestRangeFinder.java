package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {

        if (nums == null || k > nums.length) {
            return null;
        }

        int[] rsl = new int[2];
        int left = 0;
        int right = k - 1;

        while (right < nums.length) {

            HashMap<Integer, Integer> map = new HashMap<>();
            int innerRight = right;

            while (left <= innerRight) {
                map.put(nums[innerRight], 1);
                innerRight--;
            }
            if (map.size() == k) {
                rsl[0] = left;
                rsl[1] = right;
                return rsl;
            }
            left++;
            right++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}