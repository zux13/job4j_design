package ru.job4j.ood.isp.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.isp.vehicle.Car;
import ru.job4j.ood.isp.vehicle.Truck;
import ru.job4j.ood.isp.vehicle.Vehicle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConcreteParkingLotTest {

    private static final int CAR_PLACES = 10;
    private static final int TRUCK_PLACES = 2;
    private ConcreteParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        parkingLot = new ConcreteParkingLot(CAR_PLACES, TRUCK_PLACES);
    }

    @Test
    public void testParkCarSuccessfully() {
        Vehicle car = new Car();
        assertTrue(parkingLot.parkVehicle(car));
        boolean found = false;
        for (ParkingPlace place : parkingLot.getParkingPlaces()) {
            if (!place.isAvailable()) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testParkTruckOnTruckPlaceSuccessfully() {
        Vehicle truck = new Truck(3);
        assertTrue(parkingLot.parkVehicle(truck));
        List<ParkingPlace> places = parkingLot.getParkingPlaces();
        boolean found = false;
        for (int i = CAR_PLACES - 1; i < places.size(); i++) {
            if (!places.get(i).isAvailable()) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testParkTruckOnCarPlaceSuccessfully() {
        /* Занимаем места для траков */
        assertTrue(parkingLot.parkVehicle(new Truck(2)));
        assertTrue(parkingLot.parkVehicle(new Truck(3)));

        /* Паркуем на место для легковой */
        Vehicle truck = new Truck(3);
        assertTrue(parkingLot.parkVehicle(truck));
        List<ParkingPlace> places = parkingLot.getParkingPlaces();

        for (int i = 0; i < truck.getSize(); i++) {
            assertFalse(places.get(i).isAvailable());
        }
    }

    @Test
    public void testParkTruckWithInsufficientSpacesFails() {
        /* Занимаем все места */
        List<ParkingPlace> places = parkingLot.getParkingPlaces();
        for (int i = CAR_PLACES - 1; i < places.size(); i++) {
            parkingLot.parkVehicle(new Truck(2));
        }
        for (int i = 0; i < CAR_PLACES; i++) {
            parkingLot.parkVehicle(new Car());
        }
        Vehicle truck = new Truck(3);
        assertFalse(parkingLot.parkVehicle(truck));
    }

    @Test
    public void testUnparkCarSuccessfully() {
        Vehicle car = new Car();
        parkingLot.parkVehicle(car);
        parkingLot.unparkVehicle(car);
        for (ParkingPlace place : parkingLot.getParkingPlaces()) {
            assertTrue(place.isAvailable());
        }
    }

    @Test
    public void testUnparkTruckOnTruckPlaceSuccessfully() {
        Vehicle truck = new Truck(2);
        parkingLot.parkVehicle(truck);
        parkingLot.unparkVehicle(truck);
        List<ParkingPlace> places = parkingLot.getParkingPlaces();
        for (int i = CAR_PLACES - 1; i < places.size(); i++) {
            assertTrue(places.get(i).isAvailable());
        }
    }

    @Test
    public void testUnparkTruckOnCarPlaceSuccessfully() {
        /* Занимаем все места для траков */
        parkingLot.parkVehicle(new Truck(2));
        parkingLot.parkVehicle(new Truck(2));

        /* Паркуем на место для легковой */
        Vehicle truck = new Truck(3);
        parkingLot.parkVehicle(truck);
        parkingLot.unparkVehicle(truck);

        List<ParkingPlace> places = parkingLot.getParkingPlaces();
        for (int i = 0; i < truck.getSize(); i++) {
            assertTrue(places.get(i).isAvailable());
        }
    }

    @Test
    public void testUnparkNonParkedVehicleDoesNothing() {
        Vehicle car = new Car();
        parkingLot.unparkVehicle(car);

        for (ParkingPlace place : parkingLot.getParkingPlaces()) {
            assertTrue(place.isAvailable());
        }
    }
}