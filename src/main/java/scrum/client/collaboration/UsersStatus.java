package scrum.client.collaboration;

import ilarkesto.gwt.client.AGwtEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import scrum.client.BlockCollapsedListener;
import scrum.client.BlockExpandedListener;
import scrum.client.ComponentManager;
import scrum.client.DataTransferObject;
import scrum.client.ServerDataReceivedListener;
import scrum.client.UsersStatusData;
import scrum.client.admin.User;

public class UsersStatus extends GUsersStatus implements ServerDataReceivedListener, BlockCollapsedListener,
		BlockExpandedListener {

	private ComponentManager cm = ComponentManager.get();
	private UsersStatusData usersStatus = new UsersStatusData();

	public void onBlockExpanded(Object object) {
		if (object instanceof AGwtEntity) {
			addSelectedEntityId(((AGwtEntity) object).getId());
		}
	}

	public void onBlockCollapsed(Object object) {
		if (object instanceof AGwtEntity) {
			removeSelectedEntityId(((AGwtEntity) object).getId());
		}
	}

	public void onServerDataReceived(DataTransferObject data) {
		if (data.usersStatus != null) {
			usersStatus = data.usersStatus;
			log.debug("usersStatus updated:", usersStatus);
			cm.getEventBus().fireVisibleDataChanged();
		}
	}

	public List<User> getOnlineUsers() {
		List<User> ret = new ArrayList<User>();
		for (User user : cm.getProjectContext().getProject().getParticipants()) {
			if (isOnline(user)) ret.add(user);
		}
		return ret;
	}

	public boolean isOnline(User user) {
		return usersStatus.get(user.getId()).isOnline();
	}

	public Set<String> getSelectedEntitysIds(User user) {
		return usersStatus.get(user.getId()).getSelectedEntitysIds();
	}

	private void addSelectedEntityId(String id) {
		String userId = getCurrentUser().getId();
		boolean added = usersStatus.addSelectedEntityId(userId, id);
		if (added) app.callSetSelectedEntitysIds(usersStatus.get(userId).getSelectedEntitysIds());
	}

	private void removeSelectedEntityId(String id) {
		String userId = getCurrentUser().getId();
		boolean removed = usersStatus.removeSelectedEntityId(userId, id);
		if (removed) app.callSetSelectedEntitysIds(usersStatus.get(userId).getSelectedEntitysIds());
	}

	private User getCurrentUser() {
		return auth.getUser();
	}

}