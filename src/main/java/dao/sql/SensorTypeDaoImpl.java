package dao.sql;

import dao.SensorTypeDao;
import exception.PersistentException;
import hibernate.HibernateUtil;
import model.SensorType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.util.PendingException;

import java.util.List;

public class SensorTypeDaoImpl extends BaseDaoImpl implements SensorTypeDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public List<SensorType> allSensorType() throws PendingException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        List<SensorType> list = connection.createQuery("from SensorType").list();
        tx1.commit();
        close();
        return list;
    }

    @Override
    public Integer create(SensorType entity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.save(entity);
        tx1.commit();
        close();
        return 1;
    }

    @Override
    public SensorType read(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        SensorType st = connection.get(SensorType.class, identity);
        tx1.commit();
        close();
        return st;
    }

    @Override
    public void update(SensorType entity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.update(entity);
        tx1.commit();
        close();
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        SensorType st = connection.get(SensorType.class, identity);
        connection.delete(st);
        tx1.commit();
        close();
    }
}
