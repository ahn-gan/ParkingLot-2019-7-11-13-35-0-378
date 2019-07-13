package com.thoughtworks.tdd;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws Exception {
        return parkingLot.park(car);
    }

    public FetchCarResult fetch(Ticket ticket) throws Exception {
        return parkingLot.getCar(ticket);
    }
}
