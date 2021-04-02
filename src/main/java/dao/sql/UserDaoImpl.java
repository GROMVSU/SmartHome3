package dao.sql;

import dao.UserDao;
import exception.PersistentException;
import hibernate.HibernateUtil;
import model.User;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public Integer create(User user) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.save(user);
        tx1.commit();
        close();
        return 1;
    }

    @Override
    public User read(Integer identity) throws PersistentException {
//        CriteriaBuilder criteriaBuilder = connection.getCriteriaBuilder();
//        CriteriaQuery<User> builderQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> root = builderQuery.from(User.class);
//        ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class);
//        builderQuery.select(root).where(criteriaBuilder.equal(root.get("identity"), p));
//
//        TypedQuery<User> query = connection.createQuery(builderQuery);
//        query.setParameter(p, identity);
//
//        User user = query.getSingleResult();

        getConnection();
        User user = connection.get(User.class, identity);
        close();
        return user;
    }

    @Override
    public void update(User user) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        connection.update(user);
        tx1.commit();
        close();
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
        User user = connection.get(User.class, identity);
        connection.delete(user);
        tx1.commit();
        close();
    }

    @Override
    public User read(String login, String password) throws PersistentException {
        getConnection();
        Transaction tx1 = connection.beginTransaction();
//        CriteriaBuilder criteriaBuilder = connection.getCriteriaBuilder();
//        CriteriaQuery<User> builderQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> root = builderQuery.from(User.class);
//        ParameterExpression<String> p1 = criteriaBuilder.parameter(String.class);
//        ParameterExpression<String> p2 = criteriaBuilder.parameter(String.class);
//        builderQuery.select(root).where(
//                criteriaBuilder.and(
//                        criteriaBuilder.equal(root.get("login"), p1),
//                        criteriaBuilder.equal(root.get("password"), p2)
//                )
//        );
//
//        TypedQuery<User> query = connection.createQuery(builderQuery);
//        query.setParameter(p1, login);
//        query.setParameter(p2, password);
//
//        User user = query.getSingleResult();
//        connection.close();
        User user = (User) connection.createQuery("select i from User i where i.login = :login and i.password = :password")
                            .setParameter("login", login)
                            .setParameter("password", password)
                            .setMaxResults(1)
                            .uniqueResult();
        tx1.commit();
        close();
        return user;
    }

    @Override
    public List<User> allUsers() throws PersistentException {
        getConnection();
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> i = criteria.from(User.class);
        criteria.select(i);
        TypedQuery<User> query = connection.createQuery(criteria);
        List<User> users = query.getResultList();

//        List<User> users = connection.createQuery("from User").list();
        close();
        return users;
    }
}