package mqtt;

import action.ActionManagerFactory;
import action.ActionManagerFactoryImpl;
import action.admin.AdministratorAction;
import dao.sql.DaoFactoryImpl;
import exception.PersistentException;
import model.*;
import org.apache.commons.lang3.StringUtils;
import service.ScheduleService;
import service.ScoreboardService;
import service.SensorService;
import service.SensorTypeService;
import service.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class MessageParsing extends AdministratorAction {
    String message;

    public MessageParsing(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActionManagerFactory getFactory() throws PersistentException {
        return new ActionManagerFactoryImpl(new ServiceFactoryImpl(new DaoFactoryImpl()));
    }

    public void processParsing(){
        System.out.println(message);
        if (message.startsWith("100.")) {
            parse100();
        }
        if (message.startsWith("201.")) {
            parse201();
        }
        if (message.startsWith("202.")) {
            parse202();
        }
        if (message.startsWith("30.")) {
            parse30();
        }
        if (message.startsWith("40.")) {
            parse40();
        }
        if (message.startsWith("50.")) {
            parse50();
        }
        if (message.startsWith("101.")) {
            parse101();
        }

    }

    private void parse100(){
        SensorService service = null;
        try {
            service = (SensorService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(SensorService.class);
            String temp = message.replace("100.", "");
            temp = Integer.toBinaryString(Integer.valueOf(temp));
            String reversedString = StringUtils.reverse(temp);
            int id = 0;
            int state = 0;
            for (int i=0; i<reversedString.length(); i++){
                id = i+1;
                state = Character.getNumericValue(reversedString.charAt(i));
                Sensor sen = service.findById(id);
                sen.setState(state==1?true:false);
                service.update(sen);
            }
            service.close();
        } catch (PersistentException e) {
            e.printStackTrace();
        }finally {

        }
    }

    private void parse201() {
        try {
            SensorTypeService serviceType = (SensorTypeService)  new  ServiceFactoryImpl(new DaoFactoryImpl()).getService(SensorTypeService.class);
            SensorService service = (SensorService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(SensorService.class);
            String temp = message.replace("201.", "");
            int index = temp.indexOf("-");
            int id = Integer.parseInt(temp.substring(0, index));
            temp = removeChars(temp, index);
            int type = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int door = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int avaria = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int includ = Integer.parseInt(temp.substring(0, 1));
            temp = "";
            Sensor sen = new Sensor();
            sen.setIdentity(id);
            SensorType st = serviceType.findById(type);
            sen.setType(st);
            sen.setDoor(getBoolean(door));
            sen.setCrash(getBoolean(avaria));
            sen.setOn_off(getBoolean(includ));
            sen.setTitle("Sensor_"+id);
            serviceType.close();
            service.save(sen);
            service.close();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void parse202() {
        try {
        ScheduleService service = (ScheduleService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(ScheduleService.class);
        String temp = message.replace("202.", "");
        int index = temp.indexOf("-");
        int id = Integer.parseInt(temp.substring(0, index));
        temp = removeChars(temp, index);
        index = temp.indexOf("-");
        int on = Integer.parseInt(temp.substring(0, index));
        temp = removeChars(temp, index);
        index = temp.indexOf("-");
        int hourOn = Integer.parseInt(temp.substring(0, index));
        temp = removeChars(temp, index);
        index = temp.indexOf("-");
        int minuteOn = Integer.parseInt(temp.substring(0, index));
        temp = removeChars(temp, index);
        index = temp.indexOf("-");
        int hourOff = Integer.parseInt(temp.substring(0, index));
        temp = removeChars(temp, index);
        index = temp.indexOf("-");
        int minuteOff = Integer.parseInt(temp.substring(0, index));
        temp = removeChars(temp, index);
        ArrayList<Boolean> week = new ArrayList<>();
        int w = 0, a = 0;
        while(a < 7) {
            w = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            week.add(getBoolean(w));
            a++;
        }
        Week wk = new Week();
        wk.setWeek(week);
        temp = "";
        //Timestamp ts = new Timestamp((hourOn*60+minuteOn)*60*1000);
        Date timeF = new Time(hourOn, minuteOn, 0);//hourOn, minuteOn, 0
        Date timeS = new Time(hourOff, minuteOff, 0);
        Schedule sch = new Schedule(id, timeF, timeS, getBoolean(on));
        sch.setWeekN(wk);
        service.save(sch);
        service.close();
        }catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void parse30() {
        try {
            ScoreboardService service = (ScoreboardService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(ScoreboardService.class);
            String temp = message.replace("30.", "");
            int type = Integer.parseInt(temp.substring(0, 1));
            Scoreboard sc = service.getScoreboard();
            switch (type){
                case 0:
                case 1:
                    sc.setbAlarm(type==1?true:false);
                    break;
                case 2:
                    sc.setbCrash(type==1?true:false);
                    break;
                case 3:
                    sc.setbError(type==1?true:false);
                    break;
            }
            service.updateScore(sc);
            service.close();
        }catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void parse40() {
        try {
            ScoreboardService service = (ScoreboardService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(ScoreboardService.class);
            String temp = message.replace("40.", "");
            int type = Integer.parseInt(temp.substring(0, 1));
            switch (type){
                case 0:
                case 1:
                    Scoreboard sc = service.getScoreboard();
                    sc.setbShed(type==1?true:false);
                    service.updateScore(sc);
                    service.close();
                    break;
            }
        }catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void parse50() {
        try {
            ScoreboardService service = (ScoreboardService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(ScoreboardService.class);
            String temp = message.replace("50.", "");
            int type = Integer.parseInt(temp.substring(0, 1));
            switch (type){
                case 0:
                case 1:
                    Scoreboard sc = service.getScoreboard();
                    sc.setbMan(type==1?true:false);
                    service.updateScore(sc);
                    service.close();
                break;
            }
        }catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private void parse101(){
        try {
            ScoreboardService service = (ScoreboardService) new ServiceFactoryImpl(new DaoFactoryImpl()).getService(ScoreboardService.class);
            String temp = message.replace("101.", "");
            int p1 = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int p2 = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int p3 = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int p4 = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int p5 = Integer.parseInt(temp.substring(0, 1));
            temp = removeOneChar(temp, 0);
            int p6 = Integer.parseInt(temp.substring(0, 1));
            temp = "";
            Scoreboard board = new Scoreboard(getBoolean(p1), getBoolean(p2),  getBoolean(p3), getBoolean(p4), getBoolean(p5), getBoolean(p6));
            board.setIdentity(1);
            service.updateScore(board);
            service.close();
        }catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    private static String removeChars(String s, int pos) {
        return s.substring(pos + 1);
    }
    private static String removeOneChar(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
    private boolean getBoolean(int i){
        return i > 0 ? true : false;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return null;
    }
}