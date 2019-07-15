package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager extends ParkingBoy {

    private List<ParkingBoy> parkingBoyList;

    private List<ParkingLot> parkingLots;

    public ParkingLotManager() {
        super();
        this.parkingBoyList = new ArrayList<>();
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    // todo refactor
    public Ticket specifyParkingBoyToPark(ParkingBoy parkingBoy, Car car) throws CustomException {
        if (parkingBoyList.contains(parkingBoy))
            return parkingBoy.park(car);
        else
            throw new CustomException("ParkingBoy doesn't exist the manager's parkingBoyList");
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoyList.add(parkingBoy);
    }

    public Car specifyParkingBoyToFetch(ParkingBoy parkingBoy, Ticket ticket) throws CustomException {
        if (parkingBoyList.contains(parkingBoy))
            return parkingBoy.fetch(ticket);
        throw new CustomException("ParkingBoy doesn't exist the manager's parkingBoyList");
    }

    public Ticket parkCar(Car car) throws CustomException {
        super.setParkingLotList(this.parkingLots);
        return super.park(car);
    }

    public Car fetchCar(Ticket ticket) throws CustomException {
        return super.fetch(ticket);
    }
}
