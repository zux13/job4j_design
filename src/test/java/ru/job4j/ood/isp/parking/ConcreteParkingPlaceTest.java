package ru.job4j.ood.isp.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.isp.vehicle.Car;
import ru.job4j.ood.isp.vehicle.Truck;
import ru.job4j.ood.isp.vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

public class ConcreteParkingPlaceTest {

    private ConcreteParkingPlace parkingPlace;

    @BeforeEach
    public void setUp() {
        parkingPlace = new ConcreteParkingPlace();
    }

    @Test
    public void testIsAvailableInitially() {
        assertTrue(parkingPlace.isAvailable());
    }

    @Test
    public void testOccupy() {
        Vehicle car = new Car();
        parkingPlace.occupy(car);
        assertFalse(parkingPlace.isAvailable());
    }

    @Test
    public void testRelease() {
        Vehicle car = new Car();
        parkingPlace.occupy(car);
        parkingPlace.release();
        assertTrue(parkingPlace.isAvailable());
    }

    @Test
    public void testOccupyTwiceThrowsException() {
        Vehicle car = new Car();
        parkingPlace.occupy(car);
        assertThrows(IllegalStateException.class, () -> parkingPlace.occupy(car));
    }

    @Test
    public void testReleaseWhenNotOccupiedDoesNothing() {
        parkingPlace.release();
        assertTrue(parkingPlace.isAvailable());
    }

    @Test
    public void testWhenIsOccupiedByCarIsTrue() {
        Vehicle car = new Car();
        parkingPlace.occupy(car);
        assertTrue(parkingPlace.isOccupiedBy(car));
    }

    @Test
    public void testWhenIsOccupiedByCarIsFalse() {
        parkingPlace.occupy(new Car());
        assertFalse(parkingPlace.isOccupiedBy(new Truck(3)));
    }
}