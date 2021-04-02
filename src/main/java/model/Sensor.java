package model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.Objects;

@Entity
//деактивировать создание SQL-выражений INSERT и UPDATE при запуске
//@org.hibernate.annotations.DynamicInsert
//@org.hibernate.annotations.DynamicUpdate
@Table(name = "sensor")
@OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@DynamicUpdate
public class Sensor extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    private SensorType type;
    private boolean door;
    private boolean crash;
    private boolean on_off;
    private boolean state;
    private String title;


    public Sensor(SensorType type, boolean door, boolean crash, boolean on_off, boolean state, String title) {
        this.type = type;
        this.door = door;
        this.crash = crash;
        this.on_off = on_off;
        this.state = state;
        this.title = title;
    }
    public Sensor(Sensor sen) {
        this.type = sen.type;
        this.door = sen.door;
        this.crash = sen.crash;
        this.on_off = sen.on_off;
        this.title = sen.title;
        this.state = sen.state;
    }

    public Sensor() {}

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public boolean getDoor() {
        return door;
    }

    public void setDoor(boolean door) {
        this.door = door;
    }

    public boolean getCrash() {
        return crash;
    }

    public void setCrash(boolean crash) {
        this.crash = crash;
    }

    public boolean getOn_off() {
        return on_off;
    }

    public void setOn_off(boolean on) {
        this.on_off = on;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getSensorParam(){
        String text = "301.";
        text = text.concat(String.valueOf(super.getIdentity())).concat("-")
                .concat(String.valueOf(type.getIdentity()))
                    .concat(String.valueOf(boolToInt(this.door))
                        .concat(String.valueOf(boolToInt(this.crash)).
                                concat(String.valueOf(boolToInt(this.on_off)))));
        return text;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + super.getIdentity() +
                ", type=" + type.getIdentity() +
                ", door=" + door +
                ", crash=" + crash +
                ", on=" + on_off +
                ", state=" + state +
                ", name='" + title + '\'' +
                '}';
    }

    public int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sensor sensor = (Sensor) o;
        return door == sensor.door &&
                crash == sensor.crash &&
                on_off == sensor.on_off &&
                state == sensor.state &&
                type == sensor.type &&
                super.getIdentity() == sensor.getIdentity() &&
                Objects.equals(title, sensor.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, door, crash, on_off, state, title);
    }

}
