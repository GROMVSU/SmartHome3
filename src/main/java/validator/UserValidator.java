package validator;

import javax.servlet.http.HttpServletRequest;

import model.BaseEntity;
import model.Role;
import model.User;
import exception.IncorrectFormDataException;

public class UserValidator implements Validator<BaseEntity> {
	@Override
	public User validate(HttpServletRequest request) throws IncorrectFormDataException {
		User user = new User();
		String parameter = request.getParameter("identity");
		if(parameter != null) {
			try {
				user.setIdentity(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("identity", parameter);
			}
		}
		parameter = request.getParameter("login");
		if(parameter != null && !parameter.isEmpty()) {
			user.setLogin(parameter);
		} else {
			throw new IncorrectFormDataException("login", parameter);
		}
		parameter = request.getParameter("role");
		try {
			user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
		} catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new IncorrectFormDataException("role", parameter);
		}
		return user;
	}
}
