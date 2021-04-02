package action.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

import action.Action;
import action.admin.AdministratorAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import exception.PersistentException;

public class UserDeleteAction extends AdministratorAction {
    private static Logger logger = LoggerFactory.getLogger(UserDeleteAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/user/list.html");
        try {
            UserService service = factory.getService(UserService.class);
            Integer identity = Integer.parseInt(request.getParameter("identity"));
            service.delete(identity);
            forward.getAttributes().put("message", "Сотрудник успешно удалён");
            logger.info("User {} deleted user with identity {}", getAuthorizedUser().getLogin(), identity);
        } catch (NumberFormatException e) {
           logger.warn("Incorrect data was found when user {} tried to delete user", getAuthorizedUser().getLogin(), e);
        }
        return forward;
    }
}