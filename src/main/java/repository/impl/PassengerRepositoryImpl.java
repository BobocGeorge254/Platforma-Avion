package repository.impl;

import config.DatabaseConfiguration;
import mappers.PassengerMapper;
import model.Passenger;
import repository.PassengerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final PassengerMapper passengerMapper = PassengerMapper.getInstance();

    @Override
    public Object getPassengerById(UUID id) {
        String selectSql = "SELECT * FROM Passengers WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return passengerMapper.mapToPassenger(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deletePassengerById(UUID id) {
        String deleteSql = "DELETE FROM Passengers WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassengerById(UUID id, Passenger newPassenger) {
        String updateSql = "UPDATE Passengers SET first_name=?, last_name=?, email =?, phone_number = ? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(5, id.toString());
            preparedStatement.setString(1, newPassenger.getFirstName().toString());
            preparedStatement.setString(2, newPassenger.getLastName().toString());
            preparedStatement.setString(3, newPassenger.getEmail().toString());
            preparedStatement.setString(4, newPassenger.getPhoneNumber().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewPassenger(Passenger Passenger) {
        String insertSql = "INSERT INTO Passengers (id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, Passenger.getId().toString());
            preparedStatement.setString(2, Passenger.getFirstName().toString());
            preparedStatement.setString(3, Passenger.getLastName().toString());
            preparedStatement.setString(4, Passenger.getEmail().toString());
            preparedStatement.setString(5, Passenger.getPhoneNumber().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Passenger> getAllPassengers() {
        String selectSql = "SELECT * FROM Passengers";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return passengerMapper.mapToPassengerList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

}
