package action;

import java.util.Arrays;

import model.Role;

public abstract class AuthorizedUserAction extends Action {
    public AuthorizedUserAction() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}