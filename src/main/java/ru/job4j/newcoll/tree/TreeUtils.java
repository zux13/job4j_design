package ru.job4j.newcoll.tree;
import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;

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
}