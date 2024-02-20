package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        final Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            var currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        T rsl = null;
        var currentNode = head;
        for (int i = 0; i <= index; i++) {
            rsl = currentNode.item;
            currentNode = currentNode.next;
        }
        return rsl;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        var nextNode = head.next;
        var rsl = head.item;
        head.item = null;
        head.next = null;
        head = nextNode;
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final T rsl = currentNode.item;
                currentNode = currentNode.next;
                return rsl;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}