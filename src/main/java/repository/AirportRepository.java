package repository;

import model.Airport;

import java.util.List;
import java.util.UUID;

public interface AirportRepository {

    Object getAirportById(int id);

    void deleteAirportById(int id);

    void updateAirportById(int id, Airport newObject);

    void addNewAirport(Airport Airport);

    List<Airport> getAllAirports();

}