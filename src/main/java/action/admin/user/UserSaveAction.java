package action.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

import action.Action;
import action.admin.AdministratorAction;
import service.UserService;
import validator.Validator;
import validator.ValidatorFactory;
import model.User;
import exception.IncorrectFormDataException;
import exception.PersistentException;

public class UserSaveAction extends AdministratorAction {
    //private static Logger logger = Logger.getLogger(UserSaveAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/user/edit.html");
        try {
            Validator<User> validator = ValidatorFactory.createValidator(User.class);
            User user = validator.validate(request);
            UserService service = factory.getService(UserService.class);
            service.save(user);
            forward.getAttributes().put("identity", user.getIdentity());
            forward.getAttributes().put("message", "Данные сотрудника успешно сохранены");
           // logger.info(String.format("User \"%s\" saved user with identity %d", getAuthorizedUser().getLogin(), user.getIdentity()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            //logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
