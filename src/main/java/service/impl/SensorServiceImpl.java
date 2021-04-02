package service.impl;

import dao.SensorDao;
import exception.PersistentException;
import model.Sensor;
import model.SensorDto;
import service.SensorService;

import java.util.List;

public class SensorServiceImpl extends ServiceImpl implements SensorService {
    @Override
    public List<Sensor> allSensors() throws PersistentException {
        SensorDao dao = factory.createDao(SensorDao.class);
        return dao.allSensors();
    }

    @Override
    public List<SensorDto> allSensorsState() throws PersistentException {
        SensorDao dao = factory.createDao(SensorDao.class);
        return dao.allSensorsState();
    }

    @Override
    public Sensor findById(int identity) throws PersistentException {
        SensorDao dao = factory.createDao(SensorDao.class);
        Sensor sensor = dao.read(identity);
        return sensor;
    }

    @Override
    public void save(Sensor sensor) throws PersistentException {
        SensorDao dao = factory.createDao(SensorDao.class);
        Sensor sen = dao.read(sensor.getIdentity());
        if (sensor.equals(sen) || sen == null){
            dao.create(sensor);
        }else{
//            sen.setCrash(sensor.getCrash());
//            sen.setDoor(sensor.getDoor());
//            sen.setOn_off(sensor.getOn_off());
//            sen.setType(sensor.getType());
            sensor.setState(sen.getState());
            sensor.setTitle(sen.getTitle());

            dao.update(sensor);
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        SensorDao dao = factory.createDao(SensorDao.class);
        dao.delete(identity);
    }

    @Override
    public void update(Sensor sensor) throws PersistentException {
        SensorDao dao = factory.createDao(SensorDao.class);
        dao.update(sensor);
    }

    @Override
    public void close() throws PersistentException {
        factory.close();
    }
}
