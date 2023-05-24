package service.impl;

import model.enums.SeatClass;
import model.enums.SeatType;
import service.FlightService;
import model.*;

public class FlightServiceImpl implements FlightService {

    private Flight flight ;
    public FlightServiceImpl(Flight flight) {
        this.flight = flight ;
    }

    @Override
    public int getFreeSeatsEconomy() {
        int freeSeats = 0;
        for (int i = 0; i <= 140; ++i) {
            if (flight.getSeats()[i].isFree() && flight.getSeats()[i].getSeatClass() == SeatClass.ECONOMY)
                freeSeats = freeSeats + 1;
        }
        return freeSeats;
    }
    @Override
    public int getFreeSeatsBusiness() {
        int freeSeats = 0 ;
        for (int i = 0 ; i <= 140 ; ++i) {
            if (flight.getSeats()[i].isFree() && flight.getSeats()[i].getSeatClass() == SeatClass.BUSINESS)
                freeSeats = freeSeats + 1;
        }
        return freeSeats ;
    }

    @Override
    public int getFreeSeatsFirstClass() {
        int freeSeats = 0 ;
        for (int i = 0 ; i <= 140 ; ++i) {
            if (flight.getSeats()[i].isFree()  && flight.getSeats()[i].getSeatClass() == SeatClass.FIRSTCLASS)
                freeSeats = freeSeats + 1;
        }
        return freeSeats ;
    }

    @Override
    public int getWindowSeats(String seatClass) {
        int windowSeats = 0 ;
        for (int i = 0 ; i <= 140 ; ++i) {
            if (flight.getSeats()[i].isFree()  && flight.getSeats()[i].getSeatClass().toString().equals(seatClass) && flight.getSeats()[i].getSeatType() == SeatType.WINDOW)
                windowSeats = windowSeats + 1;
        }
        return windowSeats ;
    }

    @Override
    public int getMiddleSeats(String seatClass) {
        int middleSeats = 0 ;
        for (int i = 0 ; i <= 140 ; ++i) {
            if (flight.getSeats()[i].isFree() == true && flight.getSeats()[i].getSeatClass().toString().equals(seatClass) && flight.getSeats()[i].getSeatType() == SeatType.MIDDLE)
                middleSeats = middleSeats + 1;
        }
        return middleSeats ;
    }

    @Override
    public int getAisleSeats(String seatClass) {
        int aisleSeats = 0 ;
        for (int i = 0 ; i <= 140 ; ++i) {
            if (flight.getSeats()[i].isFree() == true && flight.getSeats()[i].getSeatClass().toString().equals(seatClass) && flight.getSeats()[i].getSeatType() == SeatType.AISLE)
                aisleSeats = aisleSeats + 1;
        }
        return aisleSeats ;
    }

    @Override
    public boolean reserveSeat(int noSeat) {
        if (flight.getSeats()[noSeat - 1].isFree()) {
            Seat[] seats = flight.getSeats() ;
            seats[noSeat - 1].setFree(false);
            flight.setSeats(seats); ;
            return true ;
        }
        else {
            return false ;
        }
    }
}
