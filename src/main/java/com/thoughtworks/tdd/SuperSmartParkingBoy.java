package com.thoughtworks.tdd;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingCarResult park(Car car) {
        return this.getParkingCarProcess().parkCarBySuperSmartParkingBoy(car, this.getParkingLotList());
    }

}
