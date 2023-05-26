package repository;

import model.Passenger;

import java.util.List;
import java.util.UUID;

public interface PassengerRepository {

    Object getPassengerById(UUID id);

    void deletePassengerById(UUID id);

    void updatePassengerById(UUID id, Passenger newObject);

    void addNewPassenger(Passenger Passenger);

    List<Passenger> getAllPassengers();

}