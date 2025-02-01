package ru.job4j.ood.isp.parking;

import ru.job4j.ood.isp.vehicle.Vehicle;

import java.util.List;

public interface ParkingLot {
    boolean parkVehicle(Vehicle vehicle);
    void unparkVehicle(Vehicle vehicle);
    List<ParkingPlace> getParkingPlaces();
}
