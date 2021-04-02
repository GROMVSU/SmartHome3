package service;

import exception.PersistentException;
import model.Schedule;

import java.util.List;

public interface ScheduleService extends Service {
    List<Schedule> getSchedule() throws PersistentException;

    Schedule getSchedule(int id) throws PersistentException;

    void insertSchedule(Schedule sch) throws PersistentException;

    void updateSchedule(Schedule sch) throws PersistentException;

    void deleteSchedule(Schedule sch) throws PersistentException;

    void save(Schedule sch) throws PersistentException;

    void close() throws PersistentException;
}
