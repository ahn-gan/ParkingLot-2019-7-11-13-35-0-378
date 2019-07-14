package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {

    private ParkingLot parkingLot;

    private List<ParkingBoy> parkingBoyList;

    public ParkingLotManager() {
        this.parkingBoyList = new ArrayList<>();
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    public ParkingCarResult specifyParkingBoyToPark(ParkingBoy parkingBoy, Car car) {
        if (parkingBoyList.contains(parkingBoy))
            return parkingBoy.park(car);
        else
            return new ParkingCarResult();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
