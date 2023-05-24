package repository;

import model.Pilot;

import java.util.List;
import java.util.UUID;

public interface PilotRepository {

    Object getPilotById(int id);

    void deletePilotById(int id);

    void updatePilotById(int id, Pilot newObject);

    void addNewPilot(Pilot Pilot);

    List<Pilot> getAllPilots();

}