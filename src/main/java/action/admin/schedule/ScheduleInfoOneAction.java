package action.admin.schedule;

import action.admin.AdministratorAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.PersistentException;
import model.Schedule;
import service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ScheduleInfoOneAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        ScheduleService service = factory.getService(ScheduleService.class);
        Schedule sch = service.getSchedule(Integer.parseInt(request.getParameter("id")));
        //String json = new Gson().toJson(sch);
        String json = new GsonBuilder()
                .setDateFormat("HH:mm")
                .create()
                .toJson(sch);
        response.setContentType("application/json"); //yyyy-MM-dd
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
