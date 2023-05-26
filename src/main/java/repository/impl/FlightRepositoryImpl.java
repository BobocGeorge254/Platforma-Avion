package repository.impl;

import config.DatabaseConfiguration;
import mappers.FlightMapper;
import mappers.PilotMapper;
import model.Flight;
import model.Pilot;
import repository.FlightRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlightRepositoryImpl implements FlightRepository {

    private static final FlightMapper flightMapper = FlightMapper.getInstance();
    public void addNewFlight(Flight flight) {
        String insertSql = "INSERT INTO flights (id, airline_id, departure_id, destination_id, date, pilot_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, flight.getId().toString());
            preparedStatement.setString(2, flight.getAirline().getId().toString());
            preparedStatement.setString(3, flight.getDeparture().getId().toString());
            preparedStatement.setString(4, flight.getDestination().getId().toString());
            preparedStatement.setString(5, flight.getDate());
            preparedStatement.setString(6,flight.getPilot().getId().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteFlightById(UUID id) {
        String deleteSql = "DELETE FROM flights WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getFlightById(UUID id) {
        String selectSql = "SELECT * FROM flights WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return flightMapper.mapToFlight(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Flight> getAllFlights() {
        String selectSql = "SELECT * FROM flights";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return flightMapper.mapToFlightList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }
}
