package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket park(Car car) throws CustomException {
        return this.getParkingCarProcess().parkCarBySuperSmartParkingBoy(car, this.getParkingLotList());
    }

}
