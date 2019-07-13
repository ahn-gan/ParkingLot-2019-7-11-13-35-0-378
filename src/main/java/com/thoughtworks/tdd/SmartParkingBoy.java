package com.thoughtworks.tdd;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingCarResult park(Car car) {
        return this.getParkingCarProcess().parkCarSmart(car, this.getParkingLotList());
    }
}
