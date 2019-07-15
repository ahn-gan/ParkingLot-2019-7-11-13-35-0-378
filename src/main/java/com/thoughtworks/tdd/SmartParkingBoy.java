package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingCarResult park(Car car) throws CustomException {
        return this.getParkingCarProcess().parkCarBySmartParkingBoy(car, this.getParkingLotList());
    }
}
