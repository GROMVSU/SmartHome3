package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sensor_type")
public class SensorType extends BaseEntity {

    @Column (name="title")
    private String title;

    //@OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    //private List<Sensor> sensors = new ArrayList<>();

    public SensorType() {}

    public SensorType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public List<Sensor> getSensors() {
//        return sensors;
//    }
//
//    public void setSensors(List<Sensor> sensors) {
//        this.sensors = sensors;
//    }
//
//    public void addSensor(Sensor sensor){
//        sensor.setType(this);
//        sensors.add(sensor);
//    }
//    public void removeSensor(Sensor sensor){
//        sensors.remove(sensor);
//    }

}