package action.admin.sensor;

import action.Action;
import action.admin.AdministratorAction;
import exception.PersistentException;
import model.Sensor;
import mqtt.MqttHelper;
import service.SensorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SensorUpdateAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        try {
            SensorService service = factory.getService(SensorService.class);
            int type = Integer.parseInt(request.getParameter("type"));
            int id = Integer.parseInt(request.getParameter("id"));
            String val = request.getParameter("val");
            Sensor sen = service.findById(id);
            switch (type){
                case 1:
                    sen.setDoor(Integer.parseInt(val) == 1 ? true : false);
                    break;
                case 2:
                    sen.setCrash(Integer.parseInt(val) == 1 ? true : false);
                    break;
                case 3:
                    sen.setOn_off(Integer.parseInt(val) == 1 ? true : false);
                    break;
                case 4:
                    sen.setTitle(val);
                    break;
                default:
                    break;
            }
            service.update(sen);
            response.getWriter().write("Ok");
            MqttHelper mqtt = MqttHelper.getInstance();
            mqtt.publishMessage(sen.getSensorParam());
        } catch (PersistentException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}