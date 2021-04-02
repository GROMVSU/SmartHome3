package action.admin.sensor;

import action.Action;
import action.admin.AdministratorAction;
import com.google.gson.Gson;
import exception.PersistentException;
import model.SensorDto;
import service.SensorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SensorInfoStateAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        try {
            SensorService service = factory.getService(SensorService.class);
            List<SensorDto> sensors = service.allSensorsState();
            String json = new Gson().toJson(sensors);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
         } catch (PersistentException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
