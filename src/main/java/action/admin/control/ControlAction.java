package action.admin.control;

import action.admin.AdministratorAction;
import exception.PersistentException;
import model.Scoreboard;
import service.ScoreboardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        ScoreboardService service = factory.getService(ScoreboardService.class);
        Scoreboard board = service.getScoreboard();
        request.setAttribute("board", board);
        return null;
    }
}