package action.admin.sensor;

import action.admin.AdministratorAction;
import exception.PersistentException;
import model.SensorDto;
import service.SensorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SensorsListAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        SensorService service = factory.getService(SensorService.class);
        List<SensorDto> sensors = service.allSensorsState();
        request.setAttribute("sensors", sensors);
        return null;
    }
}