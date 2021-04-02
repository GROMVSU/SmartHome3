package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class Scoreboard extends BaseEntity {

    @Column(name="alarm")
    private boolean bAlarm;
    @Column (name="avaria")
    private boolean bCrash;
    @Column (name="error")
    private boolean bError;
    @Column (name="sched")
    private boolean bShed;
    @Column (name="man")
    private boolean bMan;
    @Column (name="lamp")
    private boolean bLamp;

    public Scoreboard(){}

    public Scoreboard(boolean bAlarm, boolean bCrash, boolean bError, boolean bShed, boolean bMan, boolean bLamp) {
        this.bShed = bShed;
        this.bMan = bMan;
        this.bError = bError;
        this.bLamp = bLamp;
        this.bAlarm = bAlarm;
        this.bCrash = bCrash;
    }

    public boolean getbAlarm() {
        return bAlarm;
    }

    public void setbAlarm(boolean bAlarm) {
        this.bAlarm = bAlarm;
    }

    public boolean getbCrash() {
        return bCrash;
    }

    public void setbCrash(boolean bCrash) {
        this.bCrash = bCrash;
    }

    public boolean getbError() {
        return bError;
    }

    public void setbError(boolean bError) {
        this.bError = bError;
    }

    public boolean getbShed() {
        return bShed;
    }

    public void setbShed(boolean bShed) {
        this.bShed = bShed;
    }

    public boolean getbMan() {
        return bMan;
    }

    public void setbMan(boolean bMan) {
        this.bMan = bMan;
    }

    public boolean getbLamp() {
        return bLamp;
    }

    public void setbLamp(boolean bLamp) {
        this.bLamp = bLamp;
    }
}
