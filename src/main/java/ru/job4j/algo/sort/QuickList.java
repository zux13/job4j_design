package ru.job4j.algo.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuickList {

    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int h = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, h - 1, comparator);
        quickSort(sequence, h + 1, end, comparator);
    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        T supportElement = sequence.get(start);
        int beginToEnd = start + 1;
        int endToBegin = end;
        while (true) {
            while (beginToEnd < end && comparator.compare(sequence.get(beginToEnd), supportElement) < 0) {
                beginToEnd++;
            }
            while (comparator.compare(sequence.get(endToBegin), supportElement) > 0) {
                endToBegin--;
            }
            if (beginToEnd >= endToBegin) {
                break;
            }
            Collections.swap(sequence, beginToEnd++, endToBegin--);
        }
        Collections.swap(sequence, start, endToBegin);
        return endToBegin;
    }

}