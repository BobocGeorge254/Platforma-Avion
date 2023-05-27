package repository.impl;

import config.DatabaseConfiguration;
import mappers.PilotMapper;
import model.Flight;
import model.Ticket;
import repository.TicketRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import mappers.TicketMapper ;

public class TicketRepositoryImpl implements TicketRepository {

    private static final TicketMapper ticketMapper = TicketMapper.getInstance();
    public void addNewTicket(Ticket ticket) {
        String insertSql = "INSERT INTO TICKETS (id, passenger_id, flight_id, seat_number, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, ticket.getId().toString());
            preparedStatement.setString(2, ticket.getPassenger().getId().toString());
            preparedStatement.setString(3, ticket.getFlight().getId().toString());
            preparedStatement.setInt(4,ticket.getSeat().getNumber());
            preparedStatement.setDouble(5,ticket.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Ticket> getTicketsByFlightId(UUID flightId) {
        String selectSql = "SELECT * FROM tickets where flight_id = ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                preparedStatement.setString(1, flightId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            return ticketMapper.mapToTicketList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void deleteTicketById(UUID id) {
        String deleteSql = "DELETE FROM tickets WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
