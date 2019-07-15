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
    public Ticket park(Car car) throws CustomException {
        if (null != car) {
            Ticket ticket = new Ticket();
            parkingCarTicket.put(ticket, car);
            return ticket;
        } else {
            // car is null
            throw new CustomException("Car is null.");
        }
    }

    // fetch car when given ticket
    public Car getCar(Ticket ticket) {
        Car resultCar = parkingCarTicket.get(ticket);
        parkingCarTicket.remove(ticket);
        return resultCar;
    }
}
