package service;

import exception.PersistentException;
import model.MqttSettings;

public interface MqttService extends Service {
    MqttSettings readSettings() throws PersistentException;

    void writeSettings(MqttSettings mqtt) throws PersistentException;
}
