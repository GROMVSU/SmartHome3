package dao;

import dao.Dao;
import exception.PersistentException;
import model.Schedule;

import java.util.List;

public interface ScheduleDao extends Dao<Schedule> {
    List<Schedule> getSchedule() throws PersistentException;
}
