package action.admin.control;

import action.admin.AdministratorAction;
import com.google.gson.GsonBuilder;
import exception.PersistentException;
import model.Scoreboard;
import mqtt.MqttHelper;
import service.ScoreboardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ControlUpdateAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        try {
            ScoreboardService service = factory.getService(ScoreboardService.class);
            Scoreboard board = service.getScoreboard();
            MqttHelper mqtt = MqttHelper.getInstance();
            int type = Integer.parseInt(request.getParameter("type"));
            int id;
            int val;
            switch (type){
                case 1:
                    String json = new GsonBuilder()
                            .create()
                            .toJson(board);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    break;
                case 2:
                    id = Integer.parseInt(request.getParameter("id"));
                    val = Integer.parseInt(request.getParameter("val"));
                    String text = null;
                    switch (id){
                        case 1:
                            board.setbShed(val==1?true:false);
                            text = (val==1?"4.1":"4.0");
                            break;
                        case 2:
                            board.setbLamp(val==1?true:false);
                            break;
                        case 3:
                            board.setbMan(val==1?true:false);
                            text = (val==1?"5.1":"5.0");
                            break;
                        case 0:
                            text = (val==1?"3.1":"3.0");
//                            board.setbCrash(val==1?true:false);
//                            board.setbError(val==1?true:false);
//                            board.setbAlarm(val==1?true:false);
                            break;
                    }
                    service.updateScore(board);
                    mqtt.publishMessage(text);
                    break;
                default:
                    break;
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return null;
    }
}
