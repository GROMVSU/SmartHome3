package action.admin.schedule;

import action.admin.AdministratorAction;
import exception.PersistentException;
import model.Schedule;
import service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ScheduleSettingsAction extends AdministratorAction {

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        ScheduleService service = factory.getService(ScheduleService.class);
        List<Schedule> sch = service.getSchedule();
        request.setAttribute("sch", sch);
        return null;
    }
}
