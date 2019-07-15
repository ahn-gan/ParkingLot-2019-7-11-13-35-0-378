package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyTest {

    /*
     * *****************Story 4 **************************************
     * */

    @Test
    public void should_park_car_to_second_parking_lot_by_smart_boy_when_second_parking_lot_contains_more_empty_positions() {
        // given
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // when
        // park 6 cars by parkingBoy to the firstParkingLot
        for (int i = 0; i < 6; i++) {
            firstParkingLot.getParkingCarTicket().put(new Ticket(), new Car());
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
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);
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
}
