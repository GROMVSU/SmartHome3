package service;

import exception.PersistentException;
import model.SensorDto;
import model.Sensor;

import java.util.List;

public interface SensorService extends Service {
    List<Sensor> allSensors() throws PersistentException;

    List<SensorDto> allSensorsState() throws PersistentException;

    Sensor findById(int identity) throws PersistentException;

    void save(Sensor sensor) throws PersistentException;

    void delete(Integer identity) throws PersistentException;

    void update(Sensor sensor) throws PersistentException;

    void close() throws PersistentException;
}
