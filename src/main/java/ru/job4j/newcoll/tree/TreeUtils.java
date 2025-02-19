package ru.job4j.newcoll.tree;
import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("param root can not be null");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);

        int result = 0;
        while (!queue.isEmpty()) {
            var currentNode = queue.poll();
            currentNode.getChildren().forEach(queue::push);
            result++;
        }

        return result;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("param root can not be null");
        }

        List<T> result = new ArrayList<>();
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            var currentNode = queue.poll();
            currentNode.getChildren().forEach(queue::push);
            result.add(currentNode.getValue());
        }

        return result;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("param root can not be null");
        }

        Optional<Node<T>> foundParent = findByKey(root, parent);
        Optional<Node<T>> foundChild = findByKey(root, child);

        if (foundParent.isPresent() && foundChild.isEmpty()) {
            foundParent.get().getChildren().add(new Node<>(child));
            return true;
        }

        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("param root can not be null");
        }

        Optional<Node<T>> result = Optional.empty();
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (currentNode.getValue() == key) {
                result = Optional.of(currentNode);
                break;
            }
            currentNode.getChildren().forEach(stack::push);
        }

        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("param root can not be null");
        }

        if (root.getValue() == key) {
            return Optional.of(root);
        }

        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            var listIterator = currentNode.getChildren().listIterator();
            while (listIterator.hasNext()) {
                var currentChild = listIterator.next();
                if (currentChild.getValue() == key) {
                    listIterator.remove();
                    return Optional.of(currentChild);
                }
                stack.push(currentChild);
            }
        }

        return Optional.empty();
    }
}