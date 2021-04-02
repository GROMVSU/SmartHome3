package service.impl;

import dao.MqttDao;
import exception.PersistentException;
import model.MqttSettings;
import service.MqttService;

public class MqttServiceImpl extends ServiceImpl implements MqttService {

    @Override
    public MqttSettings readSettings() throws PersistentException {
        MqttDao dao = factory.createDao(MqttDao.class);
        return dao.read(1);
    }

    @Override
    public void writeSettings(MqttSettings mqtt) throws PersistentException {
        MqttDao dao = factory.createDao(MqttDao.class);
        dao.update(mqtt);
    }
}
