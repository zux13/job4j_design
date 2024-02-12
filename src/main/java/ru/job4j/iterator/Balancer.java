package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (!nodes.isEmpty() && source.hasNext()) {
            nodes.get(index++).add(source.next());
            index = (index == nodes.size() ? 0 : index);
        }
    }
}