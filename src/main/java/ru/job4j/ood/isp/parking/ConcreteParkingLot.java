package ru.job4j.ood.isp.parking;

import ru.job4j.ood.isp.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ConcreteParkingLot implements ParkingLot {

    private final List<ParkingPlace> parkingPlaces;
    private final int carPlaces;

    public ConcreteParkingLot(int carPlaces, int truckPlaces) {
        this.parkingPlaces = new ArrayList<>();
        this.carPlaces = carPlaces;

        for (int i = 0; i < carPlaces + truckPlaces; i++) {
            parkingPlaces.add(new ConcreteParkingPlace());
        }
    }

    private boolean parkOnTruckPlace(Vehicle vehicle) {
        for (int i = carPlaces; i < parkingPlaces.size(); i++) {
            if (parkingPlaces.get(i).isAvailable()) {
                parkingPlaces.get(i).occupy(vehicle);
                return true;
            }
        }
        return false;
    }

    private boolean parkOnCarPlace(Vehicle vehicle) {
        for (int i = 0; i < carPlaces; i++) {
            if (!isFreeSequence(i, vehicle.getSize())) {
                continue;
            }
            parkOnSequence(i, vehicle);
            return true;
        }
        return false;
    }

    private void parkOnSequence(int start, Vehicle vehicle) {
        for (int i = 0; i < vehicle.getSize(); i++) {
            parkingPlaces.get(start + i).occupy(vehicle);
        }
    }

    private boolean isFreeSequence(int start, int size) {
        if (start + size > carPlaces) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!parkingPlaces.get(start + i).isAvailable()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean parkVehicle(Vehicle vehicle) {
        return vehicle.getSize() > 1
                ? parkOnTruckPlace(vehicle) || parkOnCarPlace(vehicle)
                : parkOnCarPlace(vehicle);
    }

    @Override
    public void unparkVehicle(Vehicle vehicle) {
        for (ParkingPlace place : parkingPlaces) {
            if (place.isAvailable() || !place.isOccupiedBy(vehicle)) {
                continue;
            }
            place.release();
        }
    }

    @Override
    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }
}
