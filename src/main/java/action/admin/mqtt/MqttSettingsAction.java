package action.admin.mqtt;

import action.Action;
import action.admin.AdministratorAction;
import exception.PersistentException;
import service.MqttService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MqttSettingsAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        MqttService service = factory.getService(MqttService.class);
        request.setAttribute("mqtt", service.readSettings());
        return null;
    }
}

