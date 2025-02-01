package ru.job4j.ood.isp.parking;

import ru.job4j.ood.isp.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ConcreteParkingLot implements ParkingLot {

    private final List<ParkingPlace> parkingPlaces;
    private final int truckPlaces;

    public ConcreteParkingLot(int carPlaces, int truckPlaces) {
        this.parkingPlaces = new ArrayList<>();
        this.truckPlaces = truckPlaces;

        for (int i = 0; i < carPlaces + truckPlaces; i++) {
            parkingPlaces.add(new ConcreteParkingPlace());
        }
    }

    @Override
    public boolean parkVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public void unparkVehicle(Vehicle vehicle) {

    }

    @Override
    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }
}
