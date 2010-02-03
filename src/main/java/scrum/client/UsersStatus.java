package scrum.client;

import ilarkesto.gwt.client.AGwtEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.common.AScrumComponent;

public class UsersStatus extends AScrumComponent implements ServerDataReceivedListener, BlockCollapsedListener,
		BlockExpandedListener {

	private UsersStatusData usersStatus;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		usersStatus = new UsersStatusData();
	}

	public void onBlockExpanded(Object object) {
		if (object instanceof AGwtEntity && cm.getProjectContext().isProjectOpen()) {
			addSelectedEntityId(((AGwtEntity) object).getId());
		}
	}

	public void onBlockCollapsed(Object object) {
		if (object instanceof AGwtEntity && cm.getProjectContext().isProjectOpen()) {
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
		if (added) cm.getApp().callSetSelectedEntitysIds(usersStatus.get(userId).getSelectedEntitysIds());
	}

	private void removeSelectedEntityId(String id) {
		String userId = getCurrentUser().getId();
		boolean removed = usersStatus.removeSelectedEntityId(userId, id);
		if (removed) cm.getApp().callSetSelectedEntitysIds(usersStatus.get(userId).getSelectedEntitysIds());
	}

}