package repository;

import model.Airport;

import java.util.List;
import java.util.UUID;

public interface AirportRepository {

    Object getAirportById(UUID id);

    void deleteAirportById(UUID id);

    void updateAirportById(UUID id, Airport newObject);

    void addNewAirport(Airport Airport);

    List<Airport> getAllAirports();

}