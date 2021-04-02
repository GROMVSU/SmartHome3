package service.impl;

import dao.SensorTypeDao;
import exception.PersistentException;
import model.SensorType;
import service.SensorTypeService;

import java.util.List;

public class SensorTypeServiceImpl extends ServiceImpl implements SensorTypeService {
    @Override
    public List<SensorType> allSensorType() throws PersistentException {
        SensorTypeDao dao =  factory.createDao(SensorTypeDao.class);
        List<SensorType> list = dao.allSensorType();
        return list;
    }

    @Override
    public SensorType findById(int id) throws PersistentException {
        SensorTypeDao dao =  factory.createDao(SensorTypeDao.class);
        SensorType st = dao.read(id);
        return st;
    }

    @Override
    public void save(SensorType sensorType) throws PersistentException {
        SensorTypeDao dao =  factory.createDao(SensorTypeDao.class);
        dao.create(sensorType);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        SensorTypeDao dao =  factory.createDao(SensorTypeDao.class);
        dao.delete(id);
    }

    @Override
    public void update(SensorType sensorType) throws PersistentException {
        SensorTypeDao dao =  factory.createDao(SensorTypeDao.class);
        dao.update(sensorType);
    }

    @Override
    public void close() throws PersistentException {
        factory.close();
    }
}
