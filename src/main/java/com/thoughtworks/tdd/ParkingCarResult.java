package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Ticket;

public class ParkingCarResult {

    private Ticket ticket;

    private String errorMessage;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
