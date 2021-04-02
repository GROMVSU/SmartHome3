package action.admin.sensor;

import action.admin.AdministratorAction;
import exception.PersistentException;
import model.SensorType;
import service.SensorTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SensorTypeListAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        SensorTypeService service = factory.getService(SensorTypeService.class);
        List<SensorType> types = service.allSensorType();
        request.setAttribute("types", types);
        return null;
    }
}