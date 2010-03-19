package scrum.client.admin;

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
		return getCurrentUser().isAdmin();
	}

	@Override
	protected void onExecute() {
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
