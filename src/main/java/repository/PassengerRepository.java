package repository;

import model.Passenger;

import java.util.List;
import java.util.UUID;

public interface PassengerRepository {

    Object getPassengerById(int id);

    void deletePassengerById(int id);

    void updatePassengerById(int id, Passenger newObject);

    void addNewPassenger(Passenger Passenger);

    List<Passenger> getAllPassengers();

}