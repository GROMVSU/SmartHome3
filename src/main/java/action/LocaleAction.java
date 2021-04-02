package action;


import exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleAction extends Action {

   @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/login.jsp", false);
        String lang = request.getParameter("language");
        request.setAttribute("language", lang);
        System.out.println(lang);
        return forward;
    }
}
