package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;

public class CreateUserAction extends AScrumAction {

	public CreateUserAction(AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
	}

	@Override
	public String getLabel() {
		return "Create new User";
	}

	@Override
	public boolean isExecutable() {
		return getUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		User user = new User();
		ScrumGwtApplication.get().getDao().createUser(user);
		UserListWidget.get().showUser(user);
	}

}
