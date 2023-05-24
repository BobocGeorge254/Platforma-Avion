package mappers;

import model.Airline;
import model.Airport;
import model.Flight;
import model.Pilot;
import model.enums.*;

import mappers.*;
import repository.impl.AirlineRepositoryImpl;
import repository.impl.AirportRepositoryImpl;
import repository.impl.PilotRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightMapper {

    private static final FlightMapper INSTANCE = new FlightMapper();

    private FlightMapper() {
    }

    public static FlightMapper getInstance() {
        return INSTANCE;
    }

    public Flight mapToFlight(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Flight.FlightBuilder flightBuilder = new Flight.FlightBuilder();
            AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl() ;
            Airline airline = (Airline) airlineRepository.getAirlineById(resultSet.getInt(2)) ;

            AirportRepositoryImpl airportRepository = new AirportRepositoryImpl() ;
            Airport departure = (Airport) airportRepository.getAirportById(resultSet.getInt(3)) ;
            Airport destination = (Airport) airportRepository.getAirportById(resultSet.getInt(4)) ;

            PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl() ;
            Pilot pilot = (Pilot) pilotRepository.getPilotById(resultSet.getInt(6)) ;

            flightBuilder.setAirline(airline) ;
            flightBuilder.setDeparture(departure) ;
            flightBuilder.setDestination(destination) ;
            flightBuilder.setDate(resultSet.getString(5)) ;
            flightBuilder.setPilot(pilot) ;

            return flightBuilder.build() ;

        } else {
            throw new SQLException("No rows in ResultSet");
        }
    }


}