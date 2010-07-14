package scrum.client.admin;

import ilarkesto.gwt.client.Gwt;

public class DeleteUserAction extends GDeleteUserAction {

	public DeleteUserAction(User user) {
		super(user);
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
		if (!user.isDisabled()) return false;
		if (!getCurrentUser().isAdmin()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		if (!Gwt.confirm("Delete user " + user.getName() + "?")) return;
		getDao().deleteUser(user);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete User " + user.getName();
		}

		@Override
		protected void onUndo() {
			getDao().createUser(user);
		}

	}

}
