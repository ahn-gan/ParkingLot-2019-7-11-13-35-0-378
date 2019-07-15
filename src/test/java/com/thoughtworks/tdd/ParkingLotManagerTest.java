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

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        // build parkingBoy
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy(parkingBoy);

        Car car = new Car();

        // when
        ParkingCarResult parkingCarResult = manager.specifyParkingBoyToPark(parkingBoy, car);
        FetchCarResult fetchCarResult = manager.specifyParkingBoyToFetch(parkingBoy, parkingCarResult.getTicket());

        // then
        Assertions.assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_return_car_when_manager_park_car_to_parking_lot_then_get_it_back() {
        // given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(5));

        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLots(parkingLots);

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
        ParkingLot firstParkingLot = new ParkingLot(6);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);

        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLots(parkingLots);

        firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());

        Car car = new Car();

        // when
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);

        // then
        Assertions.assertTrue(firstParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }
//
//    @Test
//    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_to_parking_lot_manager_when_ticket_is_wrong() {
//        // given
//        Car car = new Car();
//        ParkingLot parkingLot = new ParkingLot(10);
//        ParkingLotManager parkingLotManager = new ParkingLotManager();
//        parkingLotManager.setParkingLot(parkingLot);
//
//        // when
//        parkingLotManager.parkCar(car);
//        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(new Ticket());
//
//        // then
//        Assertions.assertEquals(null, fetchCarResult.getCar());
//        Assertions.assertEquals("Unrecognized parking ticket.", fetchCarResult.getErrormessage());
//    }
//
//    @Test
//    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_to_parking_lot_manager_when_no_ticket() {
//        // given
//        Car car = new Car();
//        ParkingLot parkingLot = new ParkingLot(10);
//        ParkingLotManager parkingLotManager = new ParkingLotManager();
//        parkingLotManager.setParkingLot(parkingLot);
//
//        // when
//        parkingLotManager.parkCar(car);
//
//        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(null);
//
//        // then
//        Assertions.assertEquals(null, fetchCarResult.getCar());
//        Assertions.assertEquals("Please provide your parking ticket.", fetchCarResult.getErrormessage());
//    }
//
//    @Test
//    public void should_return_not_enough_position_for_parking_car_to_parking_lot_manager_when_parking_lot_has_no_position() {
//        // given
//        Car car = new Car();
//        int capacity = 10;
//        ParkingLot parkingLot = new ParkingLot(capacity);
//        ParkingLotManager parkingLotManager = new ParkingLotManager();
//        parkingLotManager.setParkingLot(parkingLot);
//
//        // add 10 cars to parkingLot
//        for (int i = 0; i < capacity; i++) {
//            parkingLot.getParkingCarTicket().put(new Ticket(), new Car());
//        }
//
//        // when
//        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);;
//
//        // then
//        Assertions.assertEquals(null, parkingCarResult.getTicket());
//        Assertions.assertEquals("Not enough position.", parkingCarResult.getErrorMessage());
//    }
}
