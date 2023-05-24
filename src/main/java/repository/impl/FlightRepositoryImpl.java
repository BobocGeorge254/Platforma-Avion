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
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository {

    private static final FlightMapper flightMapper = FlightMapper.getInstance();
    public void addNewFlight(Flight flight) {
        String insertSql = "INSERT INTO flights (id, airline_id, departure_id, destination_id, flight_date, pilot_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setInt(1, flight.getId());
            preparedStatement.setInt(2, flight.getAirline().getId());
            preparedStatement.setInt(3, flight.getDeparture().getId());
            preparedStatement.setInt(4, flight.getDestination().getId());
            preparedStatement.setString(5, flight.getDate());
            preparedStatement.setInt(6,flight.getPilot().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteFlightById(int id) {
        String deleteSql = "DELETE FROM flights WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getFlightById(int id) {
        String selectSql = "SELECT * FROM flights WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return flightMapper.mapToFlight(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
