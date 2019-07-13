package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingLotTest {

    /*
     * *****************Story 1 **************************************
     * */

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(parkingCarResult.getTicket());

        // then
        assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_no_ticket_when_no_car_to_park() throws Exception {
        // given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when & then
        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.park(null);
        });
    }

    @Test
    public void should_multiple_cars_when_park_to_parking_lot_then_get_them_back() throws Exception {
        // given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        ParkingCarResult firstParkingResult = parkingBoy.park(firstCar);
        FetchCarResult fetchedFirstCarResult = parkingBoy.fetch(firstParkingResult.getTicket());

        ParkingCarResult secondParkingResult = parkingBoy.park(secondCar);
        FetchCarResult fetchedSecondCarResult = parkingBoy.fetch(secondParkingResult.getTicket());

        // then
        assertSame(firstCar, fetchedFirstCarResult.getCar());
        assertSame(secondCar, fetchedSecondCarResult.getCar());
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        parkingBoy.park(car);

        Ticket wrongTicket = new Ticket();

        FetchCarResult fetchCarResult = parkingBoy.fetch(wrongTicket);

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
    }

    @Test
    public void should_not_fetch_car_when_no_ticket() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(null);

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
    }

    @Test
    public void should_no_fetch_car_when_ticket_has_already_been_used() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);
        parkingBoy.fetch(parkingCarResult.getTicket());

        FetchCarResult fetchCarResultAgain = parkingBoy.fetch(parkingCarResult.getTicket());

        // then
        Assertions.assertEquals(null, fetchCarResultAgain.getCar());
    }

    @Test
    public void should_no_ticker_for_parking_car_when_parking_lot_has_no_position() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        // parking 10 cars
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }

        // then
        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.park(car);
        });
    }

    /*
    * *****************Story 2 **************************************
    * */

    @Test
    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_when_ticket_is_wrong() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        parkingBoy.park(car);

        FetchCarResult fetchCarResult = parkingBoy.fetch(new Ticket());

        Assertions.assertEquals(null, fetchCarResult.getCar());
        Assertions.assertEquals("Unrecognized parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_when_no_ticket() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        parkingBoy.park(car);

        FetchCarResult fetchCarResult = parkingBoy.fetch(null);

        Assertions.assertEquals(null, fetchCarResult.getCar());
        Assertions.assertEquals("Please provide your parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_not_enough_position_for_parking_car_when_parking_lot_has_no_position() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        // parking 10 cars
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        ParkingCarResult parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertEquals(null, parkingCarResult.getTicket());
        Assertions.assertEquals("Not enough position.", parkingCarResult.getErrorMessage());
    }

}
