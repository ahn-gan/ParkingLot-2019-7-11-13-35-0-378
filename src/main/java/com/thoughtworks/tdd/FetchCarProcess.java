package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.CustomException;

import java.util.List;

public class FetchCarProcess {

    public FetchCarResult getCar(Ticket ticket, List<ParkingLot> parkingLotList) throws CustomException {
        FetchCarResult fetchCarResult = new FetchCarResult();
        if (null != ticket) {
            for(ParkingLot parkingLot : parkingLotList) {
                if (parkingLot.getParkingCarTicket().containsKey(ticket)) {
                    return parkingLot.getCar(ticket);
                }
            }
            // the ticket is wrong
            // because all the parkingLot have no car for the ticket
            throw new CustomException("Unrecognized parking ticket.");
//            fetchCarResult.setErrormessage("Unrecognized parking ticket.");
        } else {
            // no ticket
            throw new CustomException("Please provide your parking ticket.");
//            fetchCarResult.setErrormessage("Please provide your parking ticket.");
        }
//        return fetchCarResult;
    }

    public FetchCarResult fetchCarByManager(Ticket ticket, ParkingLot parkingLot) throws CustomException {
//        FetchCarResult fetchCarResult = new FetchCarResult();
        if (null != ticket) {
            if (parkingLot.getParkingCarTicket().containsKey(ticket)) {
                return parkingLot.getCar(ticket);
            }
            // the ticket is wrong
            // the parkingLot have no car for the ticket
            throw new CustomException("Unrecognized parking ticket.");
//            fetchCarResult.setErrormessage("Unrecognized parking ticket.");
        } else {
            // no ticket
            throw new CustomException("Please provide your parking ticket.");
//            fetchCarResult.setErrormessage("Please provide your parking ticket.");
        }
//        return fetchCarResult;
    }
}
