package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SuperSmartParkingBoyTest {

    /*
     * *****************Story 5 **************************************
     * */
    @Test
    public void should_park_car_to_second_parking_lot_by_super_smart_boy_when_second_parking_lot_has_a_larger_available_position_rate() {
        // given
        Car car = new Car();
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);
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
