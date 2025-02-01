package ru.job4j.ood.isp.parking;

import ru.job4j.ood.isp.vehicle.Vehicle;

public class ConcreteParkingPlace implements ParkingPlace {

    private Vehicle occupiedBy;

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void occupy(Vehicle vehicle) {

    }

    @Override
    public void release() {

    }
}
