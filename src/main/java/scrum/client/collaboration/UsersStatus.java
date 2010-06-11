package scrum.client.collaboration;

import ilarkesto.gwt.client.AGwtEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import scrum.client.DataTransferObject;
import scrum.client.UsersStatusData;
import scrum.client.admin.User;
import scrum.client.communication.ServerDataReceivedEvent;
import scrum.client.communication.ServerDataReceivedHandler;
import scrum.client.workspace.BlockCollapsedEvent;
import scrum.client.workspace.BlockCollapsedHandler;
import scrum.client.workspace.BlockExpandedEvent;
import scrum.client.workspace.BlockExpandedHandler;
import scrum.client.workspace.VisibleDataChangedEvent;

public class UsersStatus extends GUsersStatus implements ServerDataReceivedHandler, BlockCollapsedHandler,
		BlockExpandedHandler {

	private UsersStatusData usersStatus = new UsersStatusData();

	public void onBlockExpanded(BlockExpandedEvent event) {
		Object object = event.getObject();
		if (object instanceof AGwtEntity) {
			addSelectedEntityId(((AGwtEntity) object).getId());
		}
	}

	public void onBlockCollapsed(BlockCollapsedEvent event) {
		Object object = event.getObject();
		if (object instanceof AGwtEntity) {
			removeSelectedEntityId(((AGwtEntity) object).getId());
		}
	}

	public void onServerDataReceived(ServerDataReceivedEvent event) {
		DataTransferObject data = event.getData();
		if (data.usersStatus != null) {
			usersStatus = data.usersStatus;
			log.debug("usersStatus updated:", usersStatus);
			new VisibleDataChangedEvent().fireInCurrentScope();
		}
	}

	public List<User> getOnlineUsers() {
		List<User> ret = new ArrayList<User>();
		for (User user : project.getParticipants()) {
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