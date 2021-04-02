package service;

import exception.PersistentException;
import model.SensorType;

import java.util.List;

public interface SensorTypeService extends Service {
    List<SensorType> allSensorType() throws PersistentException;

    SensorType findById(int id) throws PersistentException;

    void save(SensorType sensorType) throws PersistentException;

    void delete(Integer id) throws PersistentException;

    void update(SensorType sensorType) throws PersistentException;

    void close() throws PersistentException;
}
