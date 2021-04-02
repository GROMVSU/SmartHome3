package controller;

import hibernate.HibernateUtil;
import model.Sensor;
import model.SensorType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class TestHibernate {
    public Session session = HibernateUtil.getSessionfactory().openSession();
    public TestHibernate(){
        System.out.println("TestHibernate");
        //addDate();
        readUsers_HQL();
        //readUsers_SQL();
        session.close();
    }

    private void readUsers_HQL(){
        System.out.println("\n\nЧтение записей : HQL");

        // HQL (Hibernate Query Language)
        String sql = "From " + Sensor.class.getSimpleName();;
        System.out.println("sql = " + sql);

        List<Sensor> sensors = session.createQuery(sql).list();

        System.out.println("Sensor.size = " + sensors.size());
        for (Iterator<Sensor> it = sensors.iterator(); it.hasNext();) {
            Sensor sensor = (Sensor) it.next();
            System.out.println(sensor.toString());
        }
    }
    private void readObjects()
    {
        System.out.println("\n\nЧтение объектов");

        String sql = "select * from USERS";
        Query query = session.createSQLQuery(sql);

        List<Object[]> rows = query.list();

//        for(Object[] row : rows) {
//            Sensor sn1 = new Sensor();
//            sn1.setIdentity(Integer.valueOf(row[0].toString()));
//            user.                  (row[1].toString());
//            user.setName                   (row[2].toString());
//
//            sn1.setCrash(true);
//            sn1.setDoor(false);
//            sn1.setOn_off(true);
//            sn1.setState(false);
//            sn1.setTitle("Sensor_1");
//            sn1.setType(t1);
//
//            System.out.println(user.toString());
//        }
    }
    private void readUsers_SQL()
    {
        System.out.println("\n\nЧтение записей : SQL");

        // Использование native SQL query
        String sql = "select * from sensor_new";
        Query query = session.createSQLQuery(sql).addEntity(Sensor.class);

        List<Sensor> sensors = query.list();

        System.out.println("sensors.size = " + sensors.size());
        for (Iterator<Sensor> it = sensors.iterator(); it.hasNext();) {
            Sensor sensor = (Sensor) it.next();
            System.out.println(sensor.toString());
        }
    }

    private void addDate() {
       if (session == null)
            return;
        SensorType t1 = new SensorType("Type 1");
        SensorType t2 = new SensorType("Type 2");
        SensorType t3 = new SensorType("Type 3");

        Transaction trans = session.beginTransaction();
        session.save(t1);
        session.save(t2);
        session.save(t3);

        Sensor sn1 = new Sensor();
        sn1.setCrash(true);
        sn1.setDoor(false);
        sn1.setOn_off(true);
        sn1.setState(false);
        sn1.setTitle("Sensor_1");
        sn1.setType(t1);

        session.saveOrUpdate(sn1);

        sn1 = new Sensor();
        sn1.setCrash(false);
        sn1.setDoor(true);
        sn1.setOn_off(false);
        sn1.setState(true);
        sn1.setTitle("Sensor_2");
        sn1.setType(t2);

        session.saveOrUpdate(sn1);

        session.flush();
        trans.commit();

        session.refresh(t1);
    }
}
