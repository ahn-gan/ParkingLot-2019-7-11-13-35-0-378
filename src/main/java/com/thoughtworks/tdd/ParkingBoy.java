package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

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

    public ParkingCarResult park(Car car) throws CustomException {
        return parkingCarProcess.parkCar(car, parkingLotList);
    }

    public FetchCarResult fetch(Ticket ticket) throws CustomException {
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
