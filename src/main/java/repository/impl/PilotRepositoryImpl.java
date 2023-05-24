package repository.impl;

import config.DatabaseConfiguration;
import mappers.PilotMapper;
import model.Pilot;
import repository.PilotRepository;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PilotRepositoryImpl implements PilotRepository {

    private static final PilotMapper pilotMapper = PilotMapper.getInstance();

    @Override
    public Object getPilotById(int id) {
        String selectSql = "SELECT * FROM Pilots WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return pilotMapper.mapToPilot(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deletePilotById(int id) {
        String deleteSql = "DELETE FROM Pilots WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePilotById(int id, Pilot newPilot) {
        String updateSql = "UPDATE Pilots SET first_name=?, last_name=?, hire_date=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(4, id);
            preparedStatement.setString(1, newPilot.getFirstName().toString());
            preparedStatement.setString(2, newPilot.getLastName().toString());
            preparedStatement.setString(3, newPilot.getHireDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewPilot(Pilot Pilot) {
        String insertSql = "INSERT INTO Pilots (id, first_name, last_name, hire_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setInt(1, Pilot.getId());
            preparedStatement.setString(2, Pilot.getFirstName().toString());
            preparedStatement.setString(3, Pilot.getLastName().toString());
            preparedStatement.setString(4, Pilot.getHireDate().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Pilot> getAllPilots() {
        String selectSql = "SELECT * FROM Pilots";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return pilotMapper.mapToPilotList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

}
