package com.thoughtworks.tdd;

import java.util.List;

public class ParkingCarProcess {

    public ParkingCarResult parkCar(Car car, List<ParkingLot> parkingLotList) {
        // park car sequentially
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getParkingCarTicket().size() < 10) {
                return parkingLot.park(car);
            }
        }
        // all the parkingLot have no position
        ParkingCarResult parkingCarResult = new ParkingCarResult();
        parkingCarResult.setErrorMessage("Not enough position.");
        return parkingCarResult;
    }
}
