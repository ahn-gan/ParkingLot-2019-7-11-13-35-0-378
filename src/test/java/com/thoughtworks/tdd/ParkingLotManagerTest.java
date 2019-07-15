package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManagerTest {

    private ParkingLotManager parkingLotManager;

    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLots;

    private Car car;

    private int capacity;

    @BeforeEach
    void setUpData() {
        // given
        capacity = 10;
        parkingLot = new ParkingLot(capacity);
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLots(parkingLots);

        car = new Car();
    }

    /*
     * *****************Story 6 **************************************
     * */
    @Test
    public void should_park_car_in_second_parking_lot_when_manager_specify_parking_boy_to_parking_lot_managed_by_that_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingLotManager.addParkingBoy(parkingBoy);
        
        // when
        ParkingCarResult parkingCarResult = parkingLotManager.specifyParkingBoyToPark(parkingBoy, car);
        FetchCarResult fetchCarResult = parkingLotManager.specifyParkingBoyToFetch(parkingBoy, parkingCarResult.getTicket());

        // then
        Assertions.assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_return_car_when_manager_park_car_to_parking_lot_then_get_it_back() {
        // given

        // when
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);

        // fetch car
        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(parkingCarResult.getTicket());

        // then
        Assertions.assertSame(car, fetchCarResult.getCar());
    }

    @Test
    public void should_park_car_to_first_parking_lot_managed_by_manager_when_manager_park_car() {
        // given

        // when
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);

        // then
        Assertions.assertTrue(parkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }

    @Test
    public void should_return_unrecognized_parking_ticket_message_for_fetching_car_to_parking_lot_manager_when_ticket_is_wrong() {

        // when
        parkingLotManager.parkCar(car);

        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(new Ticket());

        // then
        Assertions.assertNull(fetchCarResult.getCar());
        Assertions.assertEquals("Unrecognized parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_please_provide_your_parking_ticket_message_for_fetching_car_to_parking_lot_manager_when_no_ticket() {
        // when
        parkingLotManager.parkCar(car);

        FetchCarResult fetchCarResult = parkingLotManager.fetchCar(null);

        // then
        Assertions.assertNull(fetchCarResult.getCar());
        Assertions.assertEquals("Please provide your parking ticket.", fetchCarResult.getErrormessage());
    }

    @Test
    public void should_return_not_enough_position_for_parking_car_to_parking_lot_manager_when_parking_lot_has_no_position() {
        // given
        // add 10 cars to parkingLot
        for (int i = 0; i < capacity; i++) {
            parkingLot.getParkingCarTicket().put(new Ticket(), new Car());
        }

        // when
        ParkingCarResult parkingCarResult = parkingLotManager.parkCar(car);

        // then
        Assertions.assertNull(parkingCarResult.getTicket());
        Assertions.assertEquals("Not enough position.", parkingCarResult.getErrorMessage());
    }
}
