package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (column < data[row].length) {
                return true;
            } else {
                column = 0;
            }
            row++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}