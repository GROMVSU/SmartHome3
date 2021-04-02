package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import exception.PersistentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutAction extends AuthorizedUserAction {
    private static Logger logger = LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        User user = getAuthorizedUser();
        logger.info("User {} is logged out", user.getLogin());
        request.getSession(false).invalidate();
        return new Forward("/login.html");
    }
}
