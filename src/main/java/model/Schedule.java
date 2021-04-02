package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFirst;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSecond;
    private boolean onOff;
    private Week weekN;

    public Schedule() {}

    public Schedule(Schedule sch) {
        super.setIdentity(sch.getIdentity());
        this.timeFirst = sch.timeFirst;
        this.timeSecond = sch.timeSecond;
        this.onOff = sch.onOff;
    }

    public Schedule(int id, Date timeOn, Date timeOff, boolean on) {
        super.setIdentity(id);
        this.timeFirst = timeOn;
        this.timeSecond = timeOff;
        this.onOff = on;
    }

    public boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(boolean on) {
        this.onOff = on;
    }

    public Date getTimeFirst() {
        return timeFirst;
    }

    public void setTimeFirst(Date timeFirst) {
        this.timeFirst = timeFirst;
    }

    public Date getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(Date timeSecond) {
        this.timeSecond = timeSecond;
    }

    public Week getWeekN() {
        return weekN;
    }

    public void setWeekN(Week weekN) {
        this.weekN = weekN;
    }

    public String getScheduleParam(){
        SimpleDateFormat format1 = new SimpleDateFormat("HH");
        SimpleDateFormat format2 = new SimpleDateFormat("mm");
        String text = "302.";
        text = text.concat(String.valueOf(super.getIdentity())).concat("-")
                .concat(String.valueOf(boolToInt(this.onOff))).concat("-")
                .concat(format1.format(timeFirst)).concat("-")
                        .concat(format2.format(timeFirst)).concat("-")
                                .concat(format1.format(timeSecond)).concat("-")
                                        .concat(format2.format(timeSecond)).concat("-")
                                                .concat(weekN.toString());
        return text;

    }

    public int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "timeFirst=" + timeFirst +
                ", timeSecond=" + timeSecond +
                ", on=" + onOff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Schedule schedule = (Schedule) o;
        return onOff == schedule.onOff &&
                timeFirst.equals(schedule.timeFirst) &&
                timeSecond.equals(schedule.timeSecond) &&
                weekN.equals(schedule.weekN)&&
                super.getIdentity() == schedule.getIdentity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timeFirst, timeSecond, onOff, weekN);
    }
}
