package mappers;

import model.Airport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AirportMapper {

    private static final AirportMapper INSTANCE = new AirportMapper();

    private AirportMapper() {
    }

    public static AirportMapper getInstance() {
        return INSTANCE;
    }

    public Airport mapToAirport(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Airport.AirportBuilder()
                    .setId(UUID.fromString(resultSet.getString(1)))
                    .setCity(resultSet.getString(2))
                    .setAddress(resultSet.getString(3))
                    .build();
        } else {
            throw new SQLException("No rows in ResultSet");
        }
    }


    public List<Airport> mapToAirportList(ResultSet resultSet) throws SQLException {
        List<Airport> AirportList = new ArrayList<>();
        while (resultSet.next()) {
            AirportList.add(
                    new Airport.AirportBuilder()
                            .setId(UUID.fromString(resultSet.getString(1)))
                            .setCity(resultSet.getString(2))
                            .setAddress(resultSet.getString(3))
                            .build()
            );
        }
        return AirportList;
    }
}