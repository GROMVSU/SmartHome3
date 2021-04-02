package action.admin.mqtt;

import action.Action;
import action.admin.AdministratorAction;
import exception.PersistentException;
import model.MqttSettings;
import mqtt.MqttHelper;
import service.MqttService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MqttSaveAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/settings/mqttSettings.html");
        MqttService service = factory.getService(MqttService.class);
        MqttSettings mqtt = new MqttSettings();
        mqtt.setIp(request.getParameter("ip"));
        mqtt.setPort(request.getParameter("port"));
        mqtt.setPubTopic(request.getParameter("pubTopic"));
        mqtt.setSubTopic(request.getParameter("subTopic"));
        service.writeSettings(mqtt);
        MqttHelper.getInstance().myReconnect();
        forward.getAttributes().put("message", "Данные успешно сохранены");
        return forward;
    }
}