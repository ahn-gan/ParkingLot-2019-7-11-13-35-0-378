package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket, Car> parkingCarTicket;

    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingCarTicket = new HashMap<>(capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap<Ticket, Car> getParkingCarTicket() {
        return parkingCarTicket;
    }

    // park car and return ticket
    public ParkingCarResult park(Car car) throws CustomException {
        ParkingCarResult parkingCarResult = new ParkingCarResult();
        if (null != car) {
            Ticket ticket = new Ticket();
            parkingCarTicket.put(ticket, car);
            parkingCarResult.setTicket(ticket);
        } else {
            // car is null
            throw new CustomException("Car is null.");
        }
        return parkingCarResult;
    }

    // fetch car when given ticket
    public FetchCarResult getCar(Ticket ticket) {
        FetchCarResult fetchCarResult = new FetchCarResult();
        Car resultCar = parkingCarTicket.get(ticket);
        fetchCarResult.setCar(resultCar);
        parkingCarTicket.remove(ticket);
        return fetchCarResult;
    }
}
