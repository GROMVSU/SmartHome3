package dao.sql;

import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.Connection;

abstract public class BaseDaoImpl {

    protected Session connection;

    public void getConnection() {
        connection = HibernateUtil.getSessionfactory().openSession();
    }

    public void close(){
        connection.close();
    }

    public void setConnection(Session connection) {this.connection = connection;}
}