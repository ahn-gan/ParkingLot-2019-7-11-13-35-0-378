package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket, Car> parkingCarTicket;

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<>(10);
    }

    // park car and return ticket
    public ParkingCarResult park(Car car) {
        ParkingCarResult parkingCarResult = new ParkingCarResult();
        if (null != car) {
            if (parkingCarTicket.size() < 10) {
                Ticket ticket = new Ticket();
                parkingCarTicket.put(ticket, car);
                parkingCarResult.setTicket(ticket);
            } else {
                // parking lot has no position
                parkingCarResult.setErrorMessage("Not enough position.");
            }
        } else {
            // car is null
            parkingCarResult.setErrorMessage("Car is null.");
        }
        return parkingCarResult;
    }

    // fetch car when given ticket
    public FetchCarResult getCar(Ticket ticket) {
        FetchCarResult fetchCarResult = new FetchCarResult();
        if (null != ticket) {
            Car resultCar = parkingCarTicket.get(ticket);
            if (resultCar == null) {
                // ticket is wrong
                fetchCarResult.setErrormessage("Unrecognized parking ticket.");
            } else {
                fetchCarResult.setCar(resultCar);
                parkingCarTicket.remove(ticket);
            }
        } else {
            // no ticket
            fetchCarResult.setErrormessage("Please provide your parking ticket.");
        }
        return fetchCarResult;
    }
}
