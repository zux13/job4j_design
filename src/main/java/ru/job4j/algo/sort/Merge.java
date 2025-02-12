package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {

        int n1 = left.length;
        int n2 = right.length;
        int[] result = new int[n1 + n2];
        int indexLeft = 0;
        int indexRight = 0;

        for (int i = 0; i < result.length; i++) {
            if (indexLeft >= n1) {
                result[i] = right[indexRight];
                indexRight++;
                continue;
            }
            if (indexRight >= n2) {
                result[i] = left[indexLeft];
                indexLeft++;
                continue;
            }
            if (left[indexLeft] < right[indexRight]) {
                result[i] = left[indexLeft];
                indexLeft++;
            } else {
                result[i] = right[indexRight];
                indexRight++;
            }
        }

        return result;
    }
}
