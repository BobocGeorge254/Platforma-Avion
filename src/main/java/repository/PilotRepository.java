package repository;

import model.Pilot;

import java.util.List;
import java.util.UUID;

public interface PilotRepository {

    Object getPilotById(UUID id);

    void deletePilotById(UUID id);

    void updatePilotById(UUID id, Pilot newObject);

    void addNewPilot(Pilot Pilot);

    List<Pilot> getAllPilots();

}