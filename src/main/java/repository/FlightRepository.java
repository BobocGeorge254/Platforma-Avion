package repository;


import model.Flight;

public interface FlightRepository {

    Object getFlightById(int id);

    void addNewFlight(Flight flight);

    void deleteFlightById(int id) ;
}
