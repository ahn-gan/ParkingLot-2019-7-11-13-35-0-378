package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {

    private int capacity;

    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLots;

    private ParkingBoy parkingBoy;

    private Car car;

    @BeforeEach
    void setUpData() {
        // given
        capacity = 10;
        parkingLot = new ParkingLot(capacity);
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingBoy = new ParkingBoy(parkingLots);
        car = new Car();
    }

    /*
     * *****************Story 1 **************************************
     * */

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() {
        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(parkingCarResult.getTicket());

        // then
        assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_no_ticket_when_no_car_to_park() {
        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(null);

        // then
        Assertions.assertNull(parkingCarResult.getTicket());
    }

    @Test
    public void should_multiple_cars_when_park_to_parking_lot_then_get_them_back() {
        // given
        Car secondCar = new Car();

        // when
        ParkingCarResult firstParkingResult = parkingBoy.park(car);
        FetchCarResult fetchedFirstCarResult = parkingBoy.fetch(firstParkingResult.getTicket());

        ParkingCarResult secondParkingResult = parkingBoy.park(secondCar);
        FetchCarResult fetchedSecondCarResult = parkingBoy.fetch(secondParkingResult.getTicket());

        // then
        assertSame(car, fetchedFirstCarResult.getCar());
        assertSame(secondCar, fetchedSecondCarResult.getCar());
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        // when
        parkingBoy.park(car);

        Ticket wrongTicket = new Ticket();

        FetchCarResult fetchCarResult = parkingBoy.fetch(wrongTicket);

        // then
        Assertions.assertNull(fetchCarResult.getCar());
    }

    @Test
    public void should_not_fetch_car_when_no_ticket() {
        // when
        parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(null);

        // then
        Assertions.assertNull(fetchCarResult.getCar());
    }

    @Test
    public void should_no_fetch_car_when_ticket_has_already_been_used() {
        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);
        parkingBoy.fetch(parkingCarResult.getTicket());

        FetchCarResult fetchCarResultAgain = parkingBoy.fetch(parkingCarResult.getTicket());

        // then
        Assertions.assertNull(fetchCarResultAgain.getCar());
    }

    @Test
    public void should_no_ticker_for_parking_car_when_parking_lot_has_no_position() {
        // given
        // parking 10 cars
        for (int i = 0; i < capacity; i++) {
            parkingBoy.park(new Car());
        }

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertNull(parkingCarResult.getTicket());
    }

    /*
     * *****************Story 2 **************************************
     * */

    @Test
    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_when_ticket_is_wrong() {
        // when
        parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(new Ticket());

        // then
        Assertions.assertNull(fetchCarResult.getCar());
        Assertions.assertEquals("Unrecognized parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_when_no_ticket() {
        // when
        parkingBoy.park(car);

        FetchCarResult fetchCarResult = parkingBoy.fetch(null);

        // then
        Assertions.assertNull(fetchCarResult.getCar());
        Assertions.assertEquals("Please provide your parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_not_enough_position_for_parking_car_when_parking_lot_has_no_position() {
        // given
        // parking 10 cars
        for (int i = 0; i < capacity; i++) {
            parkingBoy.park(new Car());
        }

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertNull(parkingCarResult.getTicket());
        Assertions.assertEquals("Not enough position.", parkingCarResult.getErrorMessage());
    }

    /*
     * *****************Story 3 **************************************
     * */

    @Test
    public void should_return_ticket_when_second_parking_lot_has_positions() {
        // given
        ParkingLot secondParkingLot = new ParkingLot(capacity);
        parkingLots.add(secondParkingLot);
        // parking 10 cars
        for (int i = 0; i < parkingLot.getCapacity(); i++) {
            parkingLot.getParkingCarTicket().put(new Ticket(), new Car());
        }

        // when
        // parking the 11th car
        ParkingCarResult parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertNotNull(parkingCarResult.getTicket());
    }

}
