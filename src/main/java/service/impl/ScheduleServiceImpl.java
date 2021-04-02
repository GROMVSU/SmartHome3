package service.impl;

import dao.ScheduleDao;
import exception.PersistentException;
import model.Schedule;
import service.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {
    @Override
    public List<Schedule> getSchedule() throws PersistentException {
        ScheduleDao dao = factory.createDao(ScheduleDao.class);
        return dao.getSchedule();
    }

    @Override
    public Schedule getSchedule(int id) throws PersistentException {
        ScheduleDao dao = factory.createDao(ScheduleDao.class);
        return dao.read(id);
    }

    @Override
    public void insertSchedule(Schedule sch) throws PersistentException {
        ScheduleDao dao = factory.createDao(ScheduleDao.class);
        dao.create(sch);
    }

    @Override
    public void updateSchedule(Schedule sch) throws PersistentException {
        ScheduleDao dao = factory.createDao(ScheduleDao.class);
        dao.update(sch);
    }

    @Override
    public void deleteSchedule(Schedule sch) throws PersistentException {
        ScheduleDao dao = factory.createDao(ScheduleDao.class);
        dao.delete(sch.getIdentity());
    }

    @Override
    public void save(Schedule sch) throws PersistentException {
        ScheduleDao dao = factory.createDao(ScheduleDao.class);
        //Schedule schedule = dao.read(sch.getIdentity());
        //System.out.println(schedule.equals(sch));
        //if (schedule.getIdentity() != sch.getIdentity() || schedule == null){
        //    dao.create(sch);
        //}else{
        //    dao.update(sch);
        //}
        if (dao.read(sch.getIdentity()) != null){
            dao.update(sch);
        }else {
            dao.create(sch);
        }
    }

    @Override
    public void close() throws PersistentException {
        factory.close();
    }
}
