package mappers;

import model.Pilot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PilotMapper {

    private static final PilotMapper INSTANCE = new PilotMapper();

    private PilotMapper() {
    }

    public static PilotMapper getInstance() {
        return INSTANCE;
    }

    public Pilot mapToPilot(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Pilot.PilotBuilder(resultSet.getString(2),resultSet.getString(3))
                    .setId(UUID.fromString(resultSet.getString(1)))
                    .setHireDate(resultSet.getString(4))
                    .build();
        } else {
            throw new SQLException("No rows in ResultSet");
        }
    }


    public List<Pilot> mapToPilotList(ResultSet resultSet) throws SQLException {
        List<Pilot> PilotList = new ArrayList<>();
        while (resultSet.next()) {
            PilotList.add(
                    new Pilot.PilotBuilder(resultSet.getString(2),resultSet.getString(3))
                            .setId(UUID.fromString(resultSet.getString(1)))
                            .setHireDate(resultSet.getString(4))
                            .build()
            );
        }
        return PilotList;
    }
}