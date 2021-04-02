package action.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.admin.AdministratorAction;
import service.UserService;
import model.User;
import exception.PersistentException;

public class UserListAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        UserService service = factory.getService(UserService.class);
        List<User> users = service.allUsers();
        request.setAttribute("users", users);
        return null;
    }
}
