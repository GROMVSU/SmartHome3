package dao.sql;

import dao.ScoreboardDao;
import exception.PersistentException;
import hibernate.HibernateUtil;
import model.Scoreboard;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardDaoImpl extends BaseDaoImpl implements ScoreboardDao {
    @Override
    public Integer create(Scoreboard sc) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.save(sc);
        tx1.commit();
        close();
        return 1;
    }

    @Override
    public Scoreboard read(Integer id) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        Scoreboard sc = connection.get(Scoreboard.class, id);
        tx1.commit();
        close();
        return sc;
    }

    @Override
    public void update(Scoreboard sc) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.update(sc);
        tx1.commit();
        close();
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        Scoreboard sc = connection.get(Scoreboard.class, id);
        connection.delete(sc);
        tx1.commit();
        close();
    }
}
