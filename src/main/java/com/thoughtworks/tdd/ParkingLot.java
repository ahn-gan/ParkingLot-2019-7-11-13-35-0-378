package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket, Car> parkingCarTicket;

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<>(10);
    }

    // park car and return ticket
    public Ticket park(Car car) throws Exception {
        if (parkingCarTicket.size() < 10) {
            Ticket ticket = new Ticket();
            parkingCarTicket.put(ticket, car);
            return ticket;
        } else {
            throw new Exception();
        }
    }


    // fetch car when given ticket
    public Car getCar(Ticket ticket) throws Exception {
        Car resultCar = parkingCarTicket.get(ticket);
        if (resultCar == null) {
            throw new Exception();
        } else {
            parkingCarTicket.remove(ticket);
            return resultCar;
        }

    }
}
