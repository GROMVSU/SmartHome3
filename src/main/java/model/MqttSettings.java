package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mqtt")
public class MqttSettings extends BaseEntity {
    private String ip;
    private String port;
    private String pubTopic;
    private String subTopic;

    public MqttSettings(String ip, String port, String pubTopic, String subTopic) {
        this.ip = ip;
        this.port = port;
        this.pubTopic = pubTopic;
        this.subTopic = subTopic;
    }
    public MqttSettings() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPubTopic() {
        return pubTopic;
    }

    public void setPubTopic(String pubTopic) {
        this.pubTopic = pubTopic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }
}
