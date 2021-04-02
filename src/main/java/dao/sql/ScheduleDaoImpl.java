package dao.sql;

import dao.ScheduleDao;
import exception.PersistentException;
import hibernate.HibernateUtil;
import model.Schedule;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl extends BaseDaoImpl implements ScheduleDao {

    @Override
    public Integer create(Schedule sch) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.save(sch);
        tx1.commit();
        close();
        return 1;
    }

    @Override
    public Schedule read(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        Schedule sch = null;
        sch = connection.get(Schedule.class, identity);
        tx1.commit();
        close();
        return sch;
    }

    @Override
    public void update(Schedule sch) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.update(sch);
        tx1.commit();
        close();
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        Schedule sch = connection.get(Schedule.class, identity);
        connection.delete(sch);
        tx1.commit();
        close();
    }

    @Override
    public List<Schedule> getSchedule() throws PersistentException {
        List<Schedule> listSch = new ArrayList<>();
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        listSch = connection.createQuery("from Schedule ").list();
        tx1.commit();
        close();
        return listSch;
    }
}
