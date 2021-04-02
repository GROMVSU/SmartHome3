package validator;

import java.util.HashMap;
import java.util.Map;

import model.BaseEntity;
import model.User;

public class ValidatorFactory {
    private static Map<Class<? extends BaseEntity>, Class<? extends Validator<?>>> validators = new HashMap<>();

    static {

        validators.put(User.class, UserValidator.class);
    }

    @SuppressWarnings("unchecked")
    public static <Type extends BaseEntity> Validator<Type> createValidator(Class<Type> entity) {
        try {
            return (Validator<Type>) validators.get(entity).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
