package dao;

import model.SensorType;
import sun.security.util.PendingException;

import java.util.List;

public interface SensorTypeDao extends Dao<SensorType> {
    List<SensorType> allSensorType() throws PendingException;
}
