package scrum.client;

import java.util.Set;

import scrum.client.admin.User;
import scrum.client.common.AScrumComponent;

public class UsersStatus extends AScrumComponent implements ServerDataReceivedListener {

	private UsersStatusData usersStatus;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		usersStatus = new UsersStatusData();
	}

	public void onServerDataReceived(DataTransferObject data) {
		if (data.usersStatus != null) {
			usersStatus = data.usersStatus;
			cm.getUi().getWorkspace().update();
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