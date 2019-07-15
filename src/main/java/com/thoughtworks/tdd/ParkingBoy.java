package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLotList;

    private ParkingCarProcess parkingCarProcess;

    private FetchCarProcess fetchCarProcess;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
        this.parkingCarProcess = new ParkingCarProcess();
        this.fetchCarProcess = new FetchCarProcess();
    }

    public ParkingBoy() {
        this.parkingCarProcess = new ParkingCarProcess();
        this.fetchCarProcess = new FetchCarProcess();
    }

    public ParkingCarResult park(Car car) {
        return parkingCarProcess.parkCar(car, parkingLotList);
    }

    public FetchCarResult fetch(Ticket ticket) {
        return fetchCarProcess.getCar(ticket, parkingLotList);
    }

    public ParkingCarProcess getParkingCarProcess() {
        return parkingCarProcess;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }
}
