package ru.job4j.ood.isp.parking;

import ru.job4j.ood.isp.vehicle.Vehicle;

public interface ParkingPlace {
    boolean isAvailable();
    void occupy(Vehicle vehicle);
    void release();
}
