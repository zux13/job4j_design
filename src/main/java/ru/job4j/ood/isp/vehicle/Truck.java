package ru.job4j.ood.isp.vehicle;

public class Truck extends Vehicle {

    public Truck(int size) {
        super(validateSize(size));
    }

    private static int validateSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Size of Truck must be greater than 1.");
        }
        return size;
    }
}
