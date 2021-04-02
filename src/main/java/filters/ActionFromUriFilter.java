package filters;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import action.*;
import action.admin.*;
import action.admin.control.ControlAction;
import action.admin.control.ControlUpdateAction;
import action.admin.mqtt.MqttSaveAction;
import action.admin.mqtt.MqttSettingsAction;
import action.admin.schedule.ScheduleInfoOneAction;
import action.admin.schedule.ScheduleSettingsAction;
import action.admin.schedule.ScheduleUpdateAction;
import action.admin.sensor.*;
import action.admin.user.UserDeleteAction;
import action.admin.user.UserEditAction;
import action.admin.user.UserListAction;
import action.admin.user.UserSaveAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFromUriFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(ActionFromUriFilter.class);

    private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();
    private static Map<String, Class<? extends Action>> actions_ajax = new ConcurrentHashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/home", HomeAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/chooseLanguage", LocaleAction.class);
        actions.put("/profile/edit", ProfileEditAction.class);
        actions.put("/profile/save", ProfileSaveAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/sensors/state", SensorsListAction.class);
        actions.put("/sensors/settings", SensorsSettingsAction.class);
        actions.put("/schedule/settings", ScheduleSettingsAction.class);
        actions.put("/control/control", ControlAction.class);
        actions.put("/settings/mqttSettings", MqttSettingsAction.class);
        actions.put("/settings/saveMqtt", MqttSaveAction.class);
        actions.put("/sensors/sensorType", SensorTypeListAction.class);
    }
    static  {
        actions_ajax.put("/sensors/SensorsUpdateServlet", SensorUpdateAction.class);
        actions_ajax.put("/schedule/ScheduleUpdateServlet", ScheduleUpdateAction.class);
        actions_ajax.put("/sensors/SensorInfoStateServlet", SensorInfoStateAction.class);
        actions_ajax.put("/schedule/ScheduleInfoOneServlet", ScheduleInfoOneAction.class);
        actions_ajax.put("/control/ControlUpdateServlet", ControlUpdateAction.class);
        actions_ajax.put("/sensors/SensorsTypeUpdateServlet", SensorTypeUpdateAction.class);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            logger.debug("Starting of processing of request for URI {}", uri);
            int beginAction = contextPath.length();
            int endAction   = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Class<? extends Action> actionClass;
            boolean ajax = "XMLHttpRequest".equals(((HttpServletRequest) request).getHeader("X-Requested-With"));
            if (!ajax){
                actionClass = actions.get(actionName);
            }else{
                actionClass = actions_ajax.get(actionName);
            }
            try {
                Action action = actionClass.newInstance();
                action.setName(actionName);
                action.setAjax(ajax);
                httpRequest.setAttribute("action", action);
                chain.doFilter(request, response);
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                logger.error("It is impossible to create action handler object", e);
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
