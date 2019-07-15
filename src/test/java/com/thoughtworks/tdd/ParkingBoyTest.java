package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;
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
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws CustomException {
        // when
        Ticket parkingTicket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(parkingTicket);

        // then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_no_ticket_when_no_car_to_park() throws CustomException {
        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.park(null);
        });

        // then
        Assertions.assertEquals("Car is null.", e.getMessage());

    }

    @Test
    public void should_multiple_cars_when_park_to_parking_lot_then_get_them_back() throws CustomException {
        // given
        Car secondCar = new Car();

        // when
        Ticket firstParkingResult = parkingBoy.park(car);
        Car fetchedFirstCar = parkingBoy.fetch(firstParkingResult);

        Ticket secondParkingResult = parkingBoy.park(secondCar);
        Car fetchedSecondCar = parkingBoy.fetch(secondParkingResult);

        // then
        assertSame(car, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() throws CustomException {
        // given
        parkingBoy.park(car);

        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.fetch(new Ticket());
        });

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", e.getMessage());
    }

    @Test
    public void should_not_fetch_car_when_no_ticket() throws CustomException {
        // given
        parkingBoy.park(car);

        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.fetch(null);
        });

        // then
        Assertions.assertEquals("Please provide your parking ticket.", e.getMessage());
    }

    @Test
    public void should_no_fetch_car_when_ticket_has_already_been_used() throws CustomException {
        // when
        Ticket parkingCarResult = parkingBoy.park(car);
        parkingBoy.fetch(parkingCarResult);

        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.fetch(parkingCarResult);
        });

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", e.getMessage());
    }

    @Test
    public void should_no_ticker_for_parking_car_when_parking_lot_has_no_position() throws CustomException {
        // given
        // parking 10 cars
        for (int i = 0; i < capacity; i++) {
            parkingBoy.park(new Car());
        }

        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.park(car);
        });

        // then
        Assertions.assertEquals("Not enough position.", e.getMessage());
    }

    /*
     * *****************Story 2 **************************************
     * */

    @Test
    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_when_ticket_is_wrong() throws CustomException {

        parkingBoy.park(car);
        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.fetch(new Ticket());
        });

        // then
        Assertions.assertEquals("Unrecognized parking ticket.", e.getMessage());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_when_no_ticket() throws CustomException {
        // given
        parkingBoy.park(car);

        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.fetch(null);
        });

        // then
        Assertions.assertEquals("Please provide your parking ticket.", e.getMessage());
    }

    @Test
    public void should_return_not_enough_position_for_parking_car_when_parking_lot_has_no_position() throws CustomException {
        // given
        // parking 10 cars
        for (int i = 0; i < capacity; i++) {
            parkingBoy.park(new Car());
        }

        // when
        CustomException e = Assertions.assertThrows(CustomException.class, () -> {
            parkingBoy.park(car);
        });

        // then
        Assertions.assertEquals("Not enough position.", e.getMessage());
    }

    /*
     * *****************Story 3 **************************************
     * */

    @Test
    public void should_return_ticket_when_second_parking_lot_has_positions() throws CustomException {
        // given
        ParkingLot secondParkingLot = new ParkingLot(capacity);
        parkingLots.add(secondParkingLot);
        // parking 10 cars
        for (int i = 0; i < parkingLot.getCapacity(); i++) {
            parkingLot.getParkingCarTicket().put(new Ticket(), new Car());
        }

        // when
        // parking the 11th car
        Ticket parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertNotNull(parkingCarResult);
    }

}
