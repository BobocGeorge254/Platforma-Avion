package repository.impl;

import config.DatabaseConfiguration;
import model.Flight;
import model.Ticket;
import repository.TicketRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketRepositoryImpl implements TicketRepository {
    public void addNewTicket(Ticket ticket) {
        String insertSql = "INSERT INTO TICKETS (id, passenger_id, flight_id, seat_id, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setInt(1, ticket.getId());
            preparedStatement.setInt(2, ticket.getPassenger().getId());
            preparedStatement.setInt(3, ticket.getFlight().getId());
            preparedStatement.setInt(4,ticket.getSeat().getNumber());
            preparedStatement.setDouble(5,ticket.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
