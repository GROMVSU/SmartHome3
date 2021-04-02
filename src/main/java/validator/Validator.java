package validator;

import javax.servlet.http.HttpServletRequest;

import model.BaseEntity;
import exception.IncorrectFormDataException;
import model.User;

public interface Validator<Type extends BaseEntity> {
	Type validate(HttpServletRequest request) throws IncorrectFormDataException;
}
