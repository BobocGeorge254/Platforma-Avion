package mappers;

import model.*;
import repository.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TicketMapper {
    private static final TicketMapper INSTANCE = new TicketMapper();

    private TicketMapper() {
    }

    public static TicketMapper getInstance() {
        return INSTANCE;
    }

    public Ticket mapToTicket(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {

            PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl() ;
            Passenger passenger = (Passenger) passengerRepository.getPassengerById(UUID.fromString(resultSet.getString(2))) ;

            FlightRepositoryImpl flightRepository = new FlightRepositoryImpl() ;
            Flight flight = (Flight) flightRepository.getFlightById(UUID.fromString(resultSet.getString(3))) ;

            Seat seat = new Seat(resultSet.getInt(4)) ;

            return new Ticket.TicketBuilder()
                    .setId(UUID.fromString(resultSet.getString(1)))
                    .setFlight(flight)
                    .setPassenger(passenger)
                    .setSeat(seat)
                    .build() ;

        } else {
            throw new SQLException("No rows in ResultSet");
        }
    }
}
