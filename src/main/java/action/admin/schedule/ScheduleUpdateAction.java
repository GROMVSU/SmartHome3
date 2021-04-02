package action.admin.schedule;

import action.admin.AdministratorAction;
import exception.PersistentException;
import model.Schedule;
import model.Week;
import mqtt.MqttHelper;
import service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleUpdateAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        try {
            ScheduleService service = factory.getService(ScheduleService.class);
            int type = Integer.parseInt(request.getParameter("type"));
            int id = Integer.parseInt(request.getParameter("id"));
            Schedule sch = service.getSchedule(id);
            switch (type){
                case 1:
                    String val = request.getParameter("val");
                    sch.setOnOff(Integer.parseInt(val) == 1 ? true : false);
                    break;
                case 2:
                    Week week = new Week();
                    week.setW1(Integer.parseInt(request.getParameter("w1"))==1 ? true : false);
                    week.setW2(Integer.parseInt(request.getParameter("w2"))==1 ? true : false);
                    week.setW3(Integer.parseInt(request.getParameter("w3"))==1 ? true : false);
                    week.setW4(Integer.parseInt(request.getParameter("w4"))==1 ? true : false);
                    week.setW5(Integer.parseInt(request.getParameter("w5"))==1 ? true : false);
                    week.setW6(Integer.parseInt(request.getParameter("w6"))==1 ? true : false);
                    week.setW7(Integer.parseInt(request.getParameter("w7"))==1 ? true : false);
                    sch.setWeekN(week);
                    break;
                case 3:
                    String tF = request.getParameter("tF");
                    String tS = request.getParameter("tS");
                    Date dateF= new SimpleDateFormat("HH:mm").parse(tF);
                    Date dateS= new SimpleDateFormat("HH:mm").parse(tS);
                    sch.setTimeFirst(dateF);
                    sch.setTimeSecond(dateS);
                    break;
                default:
                    break;
            }
            service.updateSchedule(sch);
            response.getWriter().write("Ok");
            MqttHelper mqtt = MqttHelper.getInstance();
            mqtt.publishMessage(sch.getScheduleParam());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
