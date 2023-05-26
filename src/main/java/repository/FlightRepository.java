package repository;


import model.Flight;

import java.util.List;
import java.util.UUID;

public interface FlightRepository {

    Object getFlightById(UUID id);

    void addNewFlight(Flight flight);

    void deleteFlightById(UUID id) ;

    List <Flight> getAllFlights() ;
}
