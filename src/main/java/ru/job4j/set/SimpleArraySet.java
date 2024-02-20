package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        for (T t : this) {
            if (Objects.equals(t, value)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : this) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}