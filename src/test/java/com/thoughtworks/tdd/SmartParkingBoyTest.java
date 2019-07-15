package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyTest {

    private Car car;

    private int capacity = 10;

    private ParkingLot firstParkingLot;

    private ParkingLot secondParkingLot;

    private List<ParkingLot> parkingLots;

    private SmartParkingBoy smartParkingBoy;

    @BeforeEach
    void setUpData() {
        car = new Car();
        capacity = 10;
        firstParkingLot = new ParkingLot(capacity);
        secondParkingLot = new ParkingLot(capacity);
        parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        smartParkingBoy = new SmartParkingBoy(parkingLots);
    }
    
    /*
     * *****************Story 4 **************************************
     * */
    @Test
    public void should_park_car_to_second_parking_lot_by_smart_boy_when_second_parking_lot_contains_more_empty_positions() throws CustomException {
        // given
        // park 6 cars by parkingBoy to the firstParkingLot
        for (int i = 0; i < 6; i++) {
            firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());
        }

        // when
        // park car by smartParkingBoy to the secondParkingLot
        ParkingCarResult parkingCarResult = smartParkingBoy.park(car);

        // then
        Assertions.assertEquals(6, firstParkingLot.getParkingCarTicket().size());
        Assertions.assertEquals(1, secondParkingLot.getParkingCarTicket().size());
        Assertions.assertFalse(firstParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
        Assertions.assertTrue(secondParkingLot.getParkingCarTicket().containsKey(parkingCarResult.getTicket()));
    }

    @Test
    public void should_park_cars_alternately_to_two_parking_lot_by_smart_parking_boy_when_two_parking_lots_are_empty() throws CustomException {
        // given
        Car secondCar = new Car();

        // when
        // park car by smartParkingBoy to the secondParkingLot
        ParkingCarResult firstParkingCarResult = smartParkingBoy.park(car);
        ParkingCarResult secondParkingCarResult = smartParkingBoy.park(secondCar);

        // then
        Assertions.assertEquals(1, firstParkingLot.getParkingCarTicket().size());
        Assertions.assertEquals(1, secondParkingLot.getParkingCarTicket().size());
        Assertions.assertTrue(firstParkingLot.getParkingCarTicket().containsKey(firstParkingCarResult.getTicket()));
        Assertions.assertFalse(firstParkingLot.getParkingCarTicket().containsKey(secondParkingCarResult.getTicket()));
        Assertions.assertFalse(secondParkingLot.getParkingCarTicket().containsKey(firstParkingCarResult.getTicket()));
        Assertions.assertTrue(secondParkingLot.getParkingCarTicket().containsKey(secondParkingCarResult.getTicket()));
    }
}
