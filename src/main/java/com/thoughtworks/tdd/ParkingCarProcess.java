package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.List;

public class ParkingCarProcess {

    public Ticket parkCar(Car car, List<ParkingLot> parkingLotList) throws CustomException {
        // park car sequentially
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getParkingCarTicket().size() < parkingLot.getCapacity()) {
                return parkingLot.park(car);
            }
        }
        // all the parkingLot have no position
        throw new CustomException("Not enough position.");
    }

    public Ticket parkCarBySmartParkingBoy(Car car, List<ParkingLot> parkingLotList) throws CustomException {
        // get the parkingLot which contains more empty positions
        ParkingLot parkingLot = parkingLotList.stream().reduce((parkingLot1, parkingLot2) -> parkingLot1.getParkingCarTicket().size() > parkingLot2.getParkingCarTicket().size()? parkingLot2 : parkingLot1).orElse(null);
        return parkingLot.park(car);
    }

    public Ticket parkCarBySuperSmartParkingBoy(Car car, List<ParkingLot> parkingLotList) throws CustomException {
        // get the parkingLot which has a larger available position rate (positions available / total capacity)
        ParkingLot parkingLot = parkingLotList.stream()
                .reduce((parkingLot1, parkingLot2) -> 1- parkingLot1.getParkingCarTicket().size() / parkingLot1.getCapacity() > 1 - parkingLot2.getParkingCarTicket().size() / parkingLot2.getCapacity() ? parkingLot1 : parkingLot2).orElse(null);
        return parkingLot.park(car);
    }

}
