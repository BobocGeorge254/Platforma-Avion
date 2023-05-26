package repository.impl;

import config.DatabaseConfiguration;
import mappers.AirlineMapper;
import model.Airline;
import repository.AirlineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AirlineRepositoryImpl implements AirlineRepository {

    private static final AirlineMapper airlineMapper = AirlineMapper.getInstance();

    @Override
    public Object getAirlineById(UUID id) {
        String selectSql = "SELECT * FROM airlines WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return airlineMapper.mapToAirline(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteAirlineById(UUID id) {
        String deleteSql = "DELETE FROM airlines WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAirlineById(UUID id, Airline newAirline) {
        String updateSql = "UPDATE airlines SET name=?, type=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(3, id.toString());
            preparedStatement.setString(1, newAirline.getName());
            preparedStatement.setString(2, newAirline.getType().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewAirline(Airline airline) {
        String insertSql = "INSERT INTO airlines (id, name, type) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, airline.getId().toString());
            preparedStatement.setString(2, airline.getName());
            preparedStatement.setString(3, airline.getType().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airline> getAllAirlines() {
        String selectSql = "SELECT * FROM airlines";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return airlineMapper.mapToAirlineList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

}
