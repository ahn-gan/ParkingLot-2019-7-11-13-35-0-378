package com.thoughtworks.tdd;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingCarResult park(Car car) {
        return parkingLot.park(car);
    }

    public FetchCarResult fetch(Ticket ticket) {
        return parkingLot.getCar(ticket);
    }
}
