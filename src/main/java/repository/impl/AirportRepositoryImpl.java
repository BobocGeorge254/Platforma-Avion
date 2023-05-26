package repository.impl;

import config.DatabaseConfiguration;
import mappers.AirportMapper;
import model.Airport;
import repository.AirportRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AirportRepositoryImpl implements AirportRepository {

    private static final AirportMapper airportMapper = AirportMapper.getInstance();

    @Override
    public Object getAirportById(UUID id) {
        String selectSql = "SELECT * FROM airports WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return airportMapper.mapToAirport(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteAirportById(UUID id) {
        String deleteSql = "DELETE FROM airports WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAirportById(UUID id, Airport newAirport) {
        String updateSql = "UPDATE airports SET city=?, address=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(3, id.toString());
            preparedStatement.setString(1, newAirport.getCity().toString());
            preparedStatement.setString(2, newAirport.getAddress().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewAirport(Airport Airport) {
        String insertSql = "INSERT INTO airports (id, city, address) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, Airport.getId().toString());
            preparedStatement.setString(2, Airport.getCity().toString());
            preparedStatement.setString(3, Airport.getAddress().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airport> getAllAirports() {
        String selectSql = "SELECT * FROM airports";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return airportMapper.mapToAirportList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

}
