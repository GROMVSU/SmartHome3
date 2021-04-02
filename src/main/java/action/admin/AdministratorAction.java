package action.admin;

import action.Action;
import model.Role;

abstract public class AdministratorAction extends Action {
	public AdministratorAction() {
		getAllowRoles().add(Role.ADMINISTRATOR);
	}
}
