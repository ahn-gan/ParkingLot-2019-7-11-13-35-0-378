package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingLotTest {

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        // then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_multiple_cars_when_park_to_parking_lot_then_get_them_back() throws Exception {
        // given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Ticket firstTicket = parkingBoy.park(firstCar);
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket);

        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket);

        // then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Ticket ticket = parkingBoy.park(car);

        Ticket wrongTicket = new Ticket();

        // then
        Assertions.assertThrows(Exception.class, () -> {
            Car fetchCar = parkingBoy.fetch(wrongTicket);
        });
    }

}
