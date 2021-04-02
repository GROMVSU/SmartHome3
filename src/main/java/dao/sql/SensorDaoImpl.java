package dao.sql;

import dao.SensorDao;
import exception.PersistentException;
import hibernate.HibernateUtil;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SensorDaoImpl extends BaseDaoImpl implements SensorDao {

    @Override
    public List<Sensor> allSensors() throws PersistentException {
        getConnection();
        List<Sensor> sensors =  connection.createQuery("from Sensor ").list();
        close();
        return sensors;
    }

    @Override
    public List<SensorDto> allSensorsState() throws PersistentException {
        getConnection();
        List<SensorDto> sensors = connection.createQuery("select new model.SensorDto(s.identity, s.title, s.state) from Sensor s where s.on_off = :p")
                                            .setParameter("p", true)
                                            .setReadOnly(true)
                                            .getResultList();
        close();
        return sensors;
    }

    @Override
    public Integer create(Sensor sensor) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.save(sensor);
        tx1.commit();
        close();
        return 1;
    }

    @Override
    public Sensor read(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        Sensor sensor = connection.get(Sensor.class, identity);
        tx1.commit();
        close();
        return sensor;
    }

    @Override
    public void update(Sensor sensor) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.update(sensor);
        tx1.commit();
        close();
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        Sensor sensor = connection.get(Sensor.class, identity);
        connection.delete(sensor);
        tx1.commit();
        close();
    }
}