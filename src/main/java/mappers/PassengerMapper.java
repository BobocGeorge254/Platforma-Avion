package mappers;

import model.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerMapper {

    private static final PassengerMapper INSTANCE = new PassengerMapper();

    private PassengerMapper() {
    }

    public static PassengerMapper getInstance() {
        return INSTANCE;
    }

    public Passenger mapToPassenger(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Passenger.PassengerBuilder(resultSet.getString(2),resultSet.getString(3))
                    .setEmail(resultSet.getString(4))
                    .setPhoneNumber(resultSet.getString(5))
                    .build();
        } else {
            throw new SQLException("No rows in ResultSet");
        }
    }


    public List<Passenger> mapToPassengerList(ResultSet resultSet) throws SQLException {
        List<Passenger> PassengerList = new ArrayList<>();
        while (resultSet.next()) {
            PassengerList.add(
                    new Passenger.PassengerBuilder(resultSet.getString(2),resultSet.getString(3))
                            .setEmail(resultSet.getString(4))
                            .setPhoneNumber(resultSet.getString(5))
                            .build()
            );
        }
        return PassengerList;
    }
}