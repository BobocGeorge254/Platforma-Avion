package repository;

import model.Airline;

import java.util.List;
import java.util.UUID;

public interface AirlineRepository {

    Object getAirlineById(int id);

    void deleteAirlineById(int id);

    void updateAirlineById(int id, Airline newObject);

    void addNewAirline(Airline Airline);

    List<Airline> getAllAirlines();

}