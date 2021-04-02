package dao;

import exception.PersistentException;
import model.Sensor;
import model.SensorDto;

import java.util.List;

public interface SensorDao extends Dao<Sensor> {
    List<Sensor> allSensors() throws PersistentException;

    List<SensorDto> allSensorsState() throws PersistentException;
}
