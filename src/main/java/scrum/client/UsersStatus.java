package scrum.client;

import ilarkesto.gwt.client.AComponent;

import java.util.Set;

import scrum.client.admin.User;

public class UsersStatus extends AComponent implements ServerDataReceivedListener {

	private UsersStatusData usersStatus;

	// --- dependencies ---

	private Ui ui;

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	// --- ---

	@Override
	protected void onInitialization() {
		super.onInitialization();
		usersStatus = new UsersStatusData();
	}

	public void onServerDataReceived(DataTransferObject data) {
		if (data.usersStatus != null) {
			usersStatus = data.usersStatus;
			ui.getWorkspace().update();
		}
	}

	public boolean isOnline(User user) {
		return usersStatus.get(user.getId()).isOnline();
	}

	public Set<String> getSelectedEntitysIds(User user) {
		return usersStatus.get(user.getId()).getSelectedEntitysIds();
	}

	public void addSelectedEntityId(String id) {
		String userId = ScrumGwtApplication.get().getUser().getId();
		boolean added = usersStatus.addSelectedEntityId(userId, id);
		if (added)
			ScrumGwtApplication.get().callSetSelectedEntitysIds(usersStatus.get(userId).getSelectedEntitysIds());
	}

	public void removeSelectedEntityId(String id) {
		String userId = ScrumGwtApplication.get().getUser().getId();
		boolean removed = usersStatus.removeSelectedEntityId(userId, id);
		if (removed)
			ScrumGwtApplication.get().callSetSelectedEntitysIds(usersStatus.get(userId).getSelectedEntitysIds());
	}

}