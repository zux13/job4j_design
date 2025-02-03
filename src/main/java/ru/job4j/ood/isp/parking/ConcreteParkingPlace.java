package ru.job4j.ood.isp.parking;

import ru.job4j.ood.isp.vehicle.Vehicle;

public class ConcreteParkingPlace implements ParkingPlace {

    private Vehicle occupiedBy;

    @Override
    public boolean isAvailable() {
        return occupiedBy == null;
    }

    @Override
    public boolean isOccupiedBy(Vehicle vehicle) {
        return occupiedBy == vehicle;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        if (occupiedBy != null) {
            throw new IllegalStateException("This place is already occupied");
        }
        this.occupiedBy = vehicle;
    }

    @Override
    public void release() {
        this.occupiedBy = null;
    }
}
