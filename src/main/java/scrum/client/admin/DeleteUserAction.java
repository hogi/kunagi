package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;

public class DeleteUserAction extends AScrumAction {

	private User user;

	public DeleteUserAction(User user, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.user = user;
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this user.";
	}

	@Override
	public boolean isExecutable() {
		return getUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		ScrumGwtApplication.get().getDao().deleteUser(user);
	}

}
