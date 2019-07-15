package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManagerTest {

    /*
     * *****************Story 6 **************************************
     * */
    @Test
    public void should_park_car_in_second_parking_lot_when_manager_specify_parking_boy_to_parking_lot_managed_by_that_parking_boy() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);

        // add firstManager for firstParkingLot
        ParkingLotManager firstManager = new ParkingLotManager();
        firstManager.setParkingLot(firstParkingLot);

        // add secondManager for secondParkingLot
        ParkingLotManager secondManager = new ParkingLotManager();
        secondManager.setParkingLot(secondParkingLot);

        // add a car to firstParkingLot
        firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(secondParkingLot);

        // build parkingBoy
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // add the parkingBoy in secondManager's parkingBoyList
        secondManager.getParkingBoyList().add(parkingBoy);

        Car car = new Car();

        // when
        ParkingCarResult parkingCarResult = secondManager.specifyParkingBoyToPark(parkingBoy, car);

        // then
        // the car park in secondParkingLot by parkingBoy
        Assertions.assertTrue(secondParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));

        Assertions.assertFalse(firstParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }

    @Test
    public void should_fetch_car_by_parking_boy_when_manager_specify_parking_boy_to_fetch_from_parking_lot_managed_by_that_parking_boy() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);

        // add firstManager for firstParkingLot
        ParkingLotManager firstManager = new ParkingLotManager();
        firstManager.setParkingLot(firstParkingLot);

        // add secondManager for secondParkingLot
        ParkingLotManager secondManager = new ParkingLotManager();
        secondManager.setParkingLot(secondParkingLot);

        // add a car to firstParkingLot
        firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(secondParkingLot);

        // build parkingBoy
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // add the parkingBoy in secondManager's parkingBoyList
        secondManager.getParkingBoyList().add(parkingBoy);

        Car car = new Car();

        // when
        // park car
        ParkingCarResult parkingCarResult = secondManager.specifyParkingBoyToPark(parkingBoy, car);

        // fetch car
        FetchCarResult fetchCarResult = secondManager.specifyParkingBoyToFetch(parkingBoy, parkingCarResult.getTicket());

        // then
        Assertions.assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_return_car_when_manager_park_car_to_parking_lot_then_get_it_back() {
        // given
        ParkingLot parkingLot = new ParkingLot(10);

        // add firstManager for firstParkingLot
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(parkingLot);

        Car car = new Car();

        // when
        // park car by parkingLotManager
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);

        // fetch car
        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(parkingCarResult.getTicket());

        // then
        Assertions.assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_park_car_to_first_parking_lot_managed_by_manager_when_manager_park_car() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);

        // add firstManager for firstParkingLot
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(firstParkingLot);

        // add a car to secondParkingLot
        secondParkingLot.getParkingCarTicket().put(new Ticket(), new Car());

        Car car = new Car();

        // when
        // park car by parkingLotManager
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);

        // then
        // park car to firstParkingLot
        Assertions.assertTrue(firstParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
        // secondParkingLot doesn't exist the car parked by manager because he doesn't managed the secondParkingLot
        Assertions.assertFalse(secondParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }

    @Test
    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_to_parking_lot_manager_when_ticket_is_wrong() {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(parkingLot);

        // when
        parkingLotManager.parkCar(car);
        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(new Ticket());

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
        Assertions.assertEquals("Unrecognized parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_to_parking_lot_manager_when_no_ticket() {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(parkingLot);

        // when
        parkingLotManager.parkCar(car);

        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(null);

        // then
        Assertions.assertEquals(null, fetchCarResult.getCar());
        Assertions.assertEquals("Please provide your parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_not_enough_position_for_parking_car_to_parking_lot_manager_when_parking_lot_has_no_position() {
        // given
        Car car = new Car();
        int capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(parkingLot);

        // add 10 cars to parkingLot
        for (int i = 0; i < capacity; i++) {
            parkingLot.getParkingCarTicket().put(new Ticket(), new Car());
        }

        // when
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);;

        // then
        Assertions.assertEquals(null, parkingCarResult.getTicket());
        Assertions.assertEquals("Not enough position.", parkingCarResult.getErrorMessage());
    }
}
