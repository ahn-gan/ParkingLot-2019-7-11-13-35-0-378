package com.thoughtworks.tdd;

import java.util.List;

public class FetchCarProcess {

    public FetchCarResult getCar(Ticket ticket, List<ParkingLot> parkingLotList) {
        FetchCarResult fetchCarResult = new FetchCarResult();
        if (null != ticket) {
            for(ParkingLot parkingLot : parkingLotList) {
                if (parkingLot.getParkingCarTicket().containsKey(ticket)) {
                    return parkingLot.getCar(ticket);
                }
            }
            // the ticket is wrong
            // because all the parkingLot have no car for the ticket
            fetchCarResult.setErrormessage("Unrecognized parking ticket.");
        } else {
            // no ticket
            fetchCarResult.setErrormessage("Please provide your parking ticket.");
        }
        return fetchCarResult;
    }
}
