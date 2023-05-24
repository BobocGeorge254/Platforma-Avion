package mappers;

import model.Airline;
import model.enums.AirlineType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineMapper {

    private static final AirlineMapper INSTANCE = new AirlineMapper();

    private AirlineMapper() {
    }

    public static AirlineMapper getInstance() {
        return INSTANCE;
    }

    public Airline mapToAirline(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Airline.AirlineBuilder()
                    .setName(resultSet.getString(2))
                    .setType(AirlineType.valueOf(resultSet.getString(3)))
                    .build();
        } else {
            throw new SQLException("No rows in ResultSet");
        }
    }


    public List<Airline> mapToAirlineList(ResultSet resultSet) throws SQLException {
        List<Airline> airlineList = new ArrayList<>();
        while (resultSet.next()) {
            airlineList.add(
                    new Airline.AirlineBuilder()
                            .setName(resultSet.getString(2))
                            .setType(AirlineType.valueOf(resultSet.getString(3)))
                            .build()
            );
        }
        return airlineList;
    }
}