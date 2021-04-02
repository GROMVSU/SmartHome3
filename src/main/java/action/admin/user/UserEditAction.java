package action.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.admin.AdministratorAction;
import service.UserService;
import model.Role;
import model.User;
import exception.PersistentException;

public class UserEditAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        request.setAttribute("roles", Role.values());
        try {
            Integer identity = (Integer) request.getAttribute("identity");
            if (identity == null) {
                identity = Integer.parseInt(request.getParameter("identity"));
            }
            UserService service = factory.getService(UserService.class);
            User user = service.findById(identity);
            if (user != null) {
                request.setAttribute("user", user);
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
