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

    public ParkingCarResult parkCarBySmartParkingBoy(Car car, List<ParkingLot> parkingLotList) {
        // get the parkingLot which contains more empty positions
        ParkingLot parkingLot = parkingLotList.stream().reduce((parkingLot1, parkingLot2) -> parkingLot1.getParkingCarTicket().size() > parkingLot2.getParkingCarTicket().size()? parkingLot2 : parkingLot1).orElse(null);
        return parkingLot.park(car);
    }

    public ParkingCarResult parkCarBySuperSmartParkingBoy(Car car, List<ParkingLot> parkingLotList) {
        // get the parkingLot which has a larger available position rate (positions available / total capacity)
        ParkingLot parkingLot = parkingLotList.stream()
                .reduce((parkingLot1, parkingLot2) -> 1- parkingLot1.getParkingCarTicket().size() / 10 > 1 - parkingLot2.getParkingCarTicket().size() / 10 ? parkingLot1 : parkingLot2).orElse(null);
        return parkingLot.park(car);
    }
}
