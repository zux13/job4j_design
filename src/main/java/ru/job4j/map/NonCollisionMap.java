package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (Float.compare(count / capacity, LOAD_FACTOR) >= 0) {
            expand();
        }
        int index = indexFromKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private int indexFromKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean tableContainsEqualKeyAtIndex(int index, K key) {
        return table[index] != null
                && Objects.hashCode(table[index].key) == Objects.hashCode(key)
                && Objects.equals(table[index].key, key);
    }

    private void expand() {
        capacity = capacity << 1;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (var entry : table) {
            if (entry != null) {
                newTable[indexFromKey(entry.key)] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFromKey(key);
        if (tableContainsEqualKeyAtIndex(index, key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFromKey(key);
        if (tableContainsEqualKeyAtIndex(index, key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final private int expectedModCount = modCount;
            private int index;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i + "; hash = " + map.indexFor(i));
        }
    }
}