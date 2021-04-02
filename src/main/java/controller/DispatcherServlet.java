package controller;

import action.Action;
import action.ActionManager;
import action.ActionManagerFactory;
import action.ActionManagerFactoryImpl;
import dao.pool.ConnectionPool;
import dao.sql.DaoFactoryImpl;
import exception.PersistentException;
import hibernate.HibernateUtil;
import mqtt.MqttHelper;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    /**
     * Инициализация сервлета
     */
    public void init() {
        HibernateUtil.getSessionfactory();
        MqttHelper.getInstance();
        //TestHibernate ts = new TestHibernate();
    }

    public ActionManagerFactory getFactory() throws PersistentException {
        return new ActionManagerFactoryImpl(new ServiceFactoryImpl(new DaoFactoryImpl()));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Action action = (Action) request.getAttribute("action");
        try {
            ActionManager actionManager = getFactory().getManager();
            Action.Forward forward = actionManager.execute(action, request, response);
            actionManager.close();
            String requestedUri = request.getRequestURI();

            if (!action.getAjax()){
                if (forward != null && forward.isRedirect()) {
                    String redirectedUri = request.getContextPath() + forward.getForward();
                    log.debug("Request for URI {} id redirected to URI {}", requestedUri, redirectedUri);
                    response.sendRedirect(redirectedUri);
                } else {
                    String jspPage;
                    if (forward != null) {
                        jspPage = forward.getForward();
                    } else {
                        jspPage = action.getName() + ".jsp";
                    }
                    jspPage = "/WEB-INF/jsp" + jspPage;
                    log.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                    getServletContext().getRequestDispatcher(jspPage).forward(request, response);
                }
            }
        } catch (PersistentException e) {
            log.error("It is impossible to process request", e);
            System.out.println("DispatcherServlet");
            request.setAttribute("error", "Ошибка обработки данных");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
