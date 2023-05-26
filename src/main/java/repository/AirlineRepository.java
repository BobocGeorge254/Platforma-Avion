package repository;

import model.Airline;

import java.util.List;
import java.util.UUID;

public interface AirlineRepository {

    Object getAirlineById(UUID id);

    void deleteAirlineById(UUID id);

    void updateAirlineById(UUID id, Airline newObject);

    void addNewAirline(Airline Airline);

    List<Airline> getAllAirlines();

}