package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket, Car> parkingCarTicket;

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<>(10);
    }

    // park car and return ticket
    public Ticket park(Car car) throws Exception {
        if (null != car && parkingCarTicket.size() < 10) {
            Ticket ticket = new Ticket();
            parkingCarTicket.put(ticket, car);
            return ticket;
        } else {
            throw new Exception();
        }
    }


    // fetch car when given ticket
    public FetchCarResult getCar(Ticket ticket) throws Exception {
        Car resultCar = parkingCarTicket.get(ticket);
        FetchCarResult fetchCarResult = new FetchCarResult();
        if (resultCar == null) {
            fetchCarResult.setErrormessage("Unrecognized parking ticket.");
//            throw new CustomException("Unrecognized parking ticket.");
        } else {
            fetchCarResult.setCar(resultCar);
            parkingCarTicket.remove(ticket);
        }
        return fetchCarResult;
    }
}
