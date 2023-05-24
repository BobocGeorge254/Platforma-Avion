package service;

import java.util.Date;
import model.Airline;
import model.Airport;
import model.Pilot ;
import model.Seat ;

public interface FlightService {
    int getFreeSeatsEconomy() ;

    int getFreeSeatsBusiness() ;

    public int getFreeSeatsFirstClass() ;

    int getWindowSeats(String seatClass) ;

    int getMiddleSeats(String seatClass) ;

    int getAisleSeats(String seatClass) ;
    boolean reserveSeat(int noSeat) ;

}
