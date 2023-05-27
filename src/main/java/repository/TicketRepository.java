package repository;

import config.DatabaseConfiguration;
import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface TicketRepository {
    void addNewTicket(Ticket ticket);

    List<Ticket> getTicketsByFlightId(UUID flightId) ;

    void deleteTicketById(UUID ticketId) ;
}
