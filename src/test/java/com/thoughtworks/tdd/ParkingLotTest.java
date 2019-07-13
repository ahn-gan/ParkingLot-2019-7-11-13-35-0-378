package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingLotTest {

    /*
     * *****************Story 1 **************************************
     * */

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws Exception {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(parkingCarResult.getTicket());

        // then
        assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_no_ticket_when_no_car_to_park() {
        // given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(null);

        // then
        Assertions.assertNull(parkingCarResult.getTicket());
    }

    @Test
    public void should_multiple_cars_when_park_to_parking_lot_then_get_them_back() {
        // given
        Car firstCar = new Car();
        Car secondCar = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

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
    public void should_not_fetch_car_when_ticket_is_wrong() {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        parkingBoy.park(car);

        Ticket wrongTicket = new Ticket();

        FetchCarResult fetchCarResult = parkingBoy.fetch(wrongTicket);

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
    }

    @Test
    public void should_not_fetch_car_when_no_ticket() {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        ParkingCarResult parkingCarResult = parkingBoy.park(car);
        parkingBoy.fetch(parkingCarResult.getTicket());

        FetchCarResult fetchCarResultAgain = parkingBoy.fetch(parkingCarResult.getTicket());

        // then
        Assertions.assertEquals(null, fetchCarResultAgain.getCar());
    }

    // todo
    @Test
    public void should_no_ticker_for_parking_car_when_parking_lot_has_no_position() {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        // parking 10 cars
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }

        ParkingCarResult parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertNull(parkingCarResult.getTicket());
    }

    /*
    * *****************Story 2 **************************************
    * */

    @Test
    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_when_ticket_is_wrong() throws Exception {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        parkingBoy.park(car);
        FetchCarResult fetchCarResult = parkingBoy.fetch(new Ticket());

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
        Assertions.assertEquals("Unrecognized parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_when_no_ticket() throws Exception {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        parkingBoy.park(car);

        FetchCarResult fetchCarResult = parkingBoy.fetch(null);

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
        Assertions.assertEquals("Please provide your parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_not_enough_position_for_parking_car_when_parking_lot_has_no_position() throws Exception {
        // given
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

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


    /*
     * *****************Story 3 **************************************
     * */

    @Test
    public void should_return_ticket_when_second_parking_lot_has_positions() {
        // given
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // when
        // parking 10 cars
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        // parking the 11th car
        ParkingCarResult parkingCarResult = parkingBoy.park(car);

        // then
        Assertions.assertNotNull(parkingCarResult.getTicket());
    }

    /*
     * *****************Story 4 **************************************
     * */

    @Test
    public void should_park_car_to_second_parking_lot_by_smart_boy_when_second_parking_lot_contains_more_empty_positions() {
        // given
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // when
        // park 10 cars by parkingBoy to the firstParkingLot
        for (int i = 0; i < 6; i++) {
            parkingBoy.park(new Car());
        }

        // park car by smartParkingBoy to the secondParkingLot
        ParkingCarResult parkingCarResult = smartParkingBoy.park(car);

        // then
        Assertions.assertEquals(6, firstParkingLot.getParkingCarTicket().size());
        Assertions.assertEquals(1, secondParkingLot.getParkingCarTicket().size());
        // the firstParkingLot not exists the car parked by smartParkingBoy
        Assertions.assertFalse(firstParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
        // the secondParkingLot exists the car parked by smartParkingBoy
        Assertions.assertTrue(secondParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }

    @Test
    public void should_park_cars_alternately_to_two_parking_lot_by_smart_parking_boy_when_two_parking_lots_are_empty() {
        // given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // when

        // park car by smartParkingBoy to the secondParkingLot
        ParkingCarResult firstParkingCarResult = smartParkingBoy.park(firstCar);
        ParkingCarResult secondParkingCarResult = smartParkingBoy.park(secondCar);

        // then
        Assertions.assertEquals(1, firstParkingLot.getParkingCarTicket().size());
        Assertions.assertEquals(1, secondParkingLot.getParkingCarTicket().size());
        // the firstParkingLot exists the firstCar
        Assertions.assertTrue(firstParkingLot.getParkingCarTicket().containsKey(firstParkingCarResult.getTicket()));
        Assertions.assertFalse(firstParkingLot.getParkingCarTicket().containsKey(secondParkingCarResult.getTicket()));
        // the secondParkingLot exists the car parked by smartParkingBoy
        Assertions.assertFalse(secondParkingLot.getParkingCarTicket().containsKey(firstParkingCarResult.getTicket()));
        Assertions.assertTrue(secondParkingLot.getParkingCarTicket().containsKey(secondParkingCarResult.getTicket()));
    }

    /*
     * *****************Story 5 **************************************
     * */
    @Test
    public void should_park_car_to_second_parking_lot_by_super_smart_boy_when_second_parking_lot_has_a_larger_available_position_rate() {
        // given
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        // parking 2 cars in firstParkingLot
        firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());
        firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());

        // parking 1 car in secondParkingLot
        secondParkingLot.getParkingCarTicket().put(new Ticket(), new Car());

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        // when
        // park car by superSmartParkingBoy to the parkingLot which has a larger available position rate (positions available / total capacity
        ParkingCarResult parkingCarResult = superSmartParkingBoy.park(car);

        // then
        Assertions.assertEquals(2, firstParkingLot.getParkingCarTicket().size());
        Assertions.assertEquals(2, secondParkingLot.getParkingCarTicket().size());
        // the firstParkingLot not exists the car parked by smartParkingBoy
        Assertions.assertFalse(firstParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
        // the secondParkingLot exists the car parked by smartParkingBoy
        Assertions.assertTrue(secondParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }
}
