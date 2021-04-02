package dao.sql;

import dao.MqttDao;
import exception.PersistentException;
import hibernate.HibernateUtil;
import model.MqttSettings;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MqttDaoImpl extends BaseDaoImpl implements MqttDao {

    @Override
    public Integer create(MqttSettings mqtt) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.saveOrUpdate(mqtt);
        tx1.commit();
        close();
        return 1;
    }

    @Override
    public MqttSettings read(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        MqttSettings mqtt = connection.get(MqttSettings.class, identity);
        tx1.commit();
        close();
        return mqtt;
    }

    @Override
    public void update(MqttSettings mqtt) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.update(mqtt);
        tx1.commit();
        close();
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        MqttSettings mqtt = connection.get(MqttSettings.class, identity);
        connection.delete(mqtt);
        tx1.commit();
        close();
    }
}
