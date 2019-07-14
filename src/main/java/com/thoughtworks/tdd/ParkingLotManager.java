package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {

    private ParkingLot parkingLot;

    private List<ParkingBoy> parkingBoyList;

    private ParkingCarProcess parkingCarProcess;

    private FetchCarProcess fetchCarProcess;

    public ParkingLotManager() {
        this.parkingBoyList = new ArrayList<>();
        this.parkingCarProcess = new ParkingCarProcess();
        this.fetchCarProcess = new FetchCarProcess();
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

    public FetchCarResult specifyParkingBoyToFetch(ParkingBoy parkingBoy, Ticket ticket) {
        if (parkingBoyList.contains(parkingBoy))
            return parkingBoy.fetch(ticket);
        else
            return new FetchCarResult();
    }

    public ParkingCarResult parkCar(Car car) {
        return parkingCarProcess.parkCarByManager(car, parkingLot);
    }

    public FetchCarResult fetchCar(Ticket ticket) {
        return fetchCarProcess.fetchCarByManager(ticket, parkingLot);
    }
}
