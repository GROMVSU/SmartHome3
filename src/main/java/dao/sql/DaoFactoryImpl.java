package dao.sql;

import dao.*;
import dao.DaoFactory;
import dao.UserDao;
import dao.pool.ConnectionPool;
import exception.PersistentException;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DaoFactoryImpl implements DaoFactory {

    private static Map<Class<? extends Dao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();
    static {
        classes.put(UserDao.class, UserDaoImpl.class);
        classes.put(SensorDao.class, SensorDaoImpl.class);
        classes.put(ScheduleDao.class, ScheduleDaoImpl.class);
        classes.put(ScoreboardDao.class, ScoreboardDaoImpl.class);
        classes.put(MqttDao.class, MqttDaoImpl.class);
        classes.put(SensorTypeDao.class, SensorTypeDaoImpl.class);
    }

    //private Connection connection;
    //private Session connection;

    public DaoFactoryImpl() throws PersistentException {
        //connection = ConnectionPool.getInstance().getConnection();
       // connection = HibernateUtil.getSessionfactory().openSession();
       // try {
            //connection.setAutoCommit(true);
        //} catch (SQLException e) {
            //logger.error("It is impossible to turn off autocommiting for database connection", e);
        //    throw new PersistentException(e);
       // }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException {
        Class<? extends BaseDaoImpl> value = classes.get(key);
        if (value != null) {
            try {
                BaseDaoImpl dao = value.newInstance();
                //dao.setConnection(connection);
                return (Type) dao;
            } catch (InstantiationException | IllegalAccessException e) {
                //logger.error("It is impossible to create data access object", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        //connection.close();
    }
}