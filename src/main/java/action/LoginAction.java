package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import model.Role;
import model.User;
import exception.PersistentException;

public class LoginAction extends Action {
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

    static {
        menu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem("/home.html", "Главная", 0),
                new MenuItem("/sensors/state.html", "Датчики", 1),
                new MenuItem("/sensors/state.html", "Состояние", 0),
                new MenuItem("/sensors/sensorType.html", "Типы датчиков", 0),
                new MenuItem("/sensors/settings.html", "Настройка", 2),
                new MenuItem("/schedule/settings.html", "Табло", 0),
                new MenuItem("/control/control.html", "Управление", 0),
                new MenuItem("/settings/mqttSettings.html", "Настройки", 1),
                new MenuItem("/settings/mqttSettings.html", "Mqtt", 0),
                new MenuItem("/user/list.html", "Пользователи", 0),
                new MenuItem("/profile/edit.html", "Профиль", 2)
        )));
    }

    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login != null && password != null) {
            UserService service = factory.getService(UserService.class);
            User user = service.findByLoginAndPassword(login, password);
            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                session.setAttribute("menu", menu.get(user.getRole()));
                logger.info("user {} is logged in from {} ({}:{})", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
                return new Forward("/index.html");
            } else {
                request.setAttribute("message", "Имя пользователя или пароль не опознанны");
                logger.info("user {} unsuccessfully tried to log in from {} ({}:{})", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
            }
        }
        return null;
    }
}