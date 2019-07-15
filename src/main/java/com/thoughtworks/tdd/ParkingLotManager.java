package com.thoughtworks.tdd;

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
    public ParkingCarResult specifyParkingBoyToPark(ParkingBoy parkingBoy, Car car) {
        if (parkingBoyList.contains(parkingBoy))
            return parkingBoy.park(car);
        else
            return new ParkingCarResult();
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

    public FetchCarResult specifyParkingBoyToFetch(ParkingBoy parkingBoy, Ticket ticket) {
        if (parkingBoyList.contains(parkingBoy))
            return parkingBoy.fetch(ticket);
        else
            return new FetchCarResult();
    }

    public ParkingCarResult parkCar(Car car) {
        super.setParkingLotList(this.parkingLots);
        return super.park(car);
    }

    public FetchCarResult fetchCar(Ticket ticket) {
        return super.fetch(ticket);
    }
}
