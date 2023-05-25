package repository;


import model.Flight;

import java.util.List;

public interface FlightRepository {

    Object getFlightById(int id);

    void addNewFlight(Flight flight);

    void deleteFlightById(int id) ;

    List <Flight> getAllFlights() ;
}
