package ru.job4j.ood.isp.vehicle;

public abstract class Vehicle {

    private final int size;

    public Vehicle(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
