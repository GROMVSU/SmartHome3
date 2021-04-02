package action.admin.sensor;

import action.Action;
import action.admin.AdministratorAction;
import com.google.gson.GsonBuilder;
import exception.PersistentException;
import model.SensorType;
import service.SensorTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SensorTypeUpdateAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        SensorTypeService service = factory.getService(SensorTypeService.class);
        int type = Integer.parseInt(request.getParameter("type"));
        int id = Integer.parseInt(request.getParameter("id"));

        String val;
        switch (type){
            case 1:
                val = request.getParameter("val");
                SensorType st = service.findById(id);
                st.setTitle(val);
                service.update(st);
                break;
            case 2:
                service.delete(id);
                break;
            default:
                break;
        }
        return null;
    }
}