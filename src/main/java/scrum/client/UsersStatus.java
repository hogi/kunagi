package scrum.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UsersStatus implements Serializable, IsSerializable {

	private Map<String, UserStatus> statuses = new HashMap<String, UserStatus>();

	public boolean addSelectedEntityId(String userId, String entityId) {
		synchronized (statuses) {
			return get(userId).selectedEntitysIds.add(entityId);
		}
	}

	public boolean removeSelectedEntityId(String userId, String entityId) {
		synchronized (statuses) {
			return get(userId).selectedEntitysIds.remove(entityId);
		}
	}

	public void setUsersSelectedEntities(String userId, Set<String> ids) {
		synchronized (statuses) {
			get(userId).selectedEntitysIds = ids;
		}
	}

	public void setOnlineUsers(Set<String> userIds) {
		synchronized (statuses) {
			Set<String> updated = new HashSet<String>();
			for (Map.Entry<String, UserStatus> entry : statuses.entrySet()) {
				String userId = entry.getKey();
				entry.getValue().online = userIds.contains(userId);
				updated.add(userId);
			}
			for (String userId : userIds) {
				if (updated.contains(userId)) continue;
				get(userId).online = false;
			}
		}
	}

	public UserStatus get(String userId) {
		synchronized (statuses) {
			UserStatus status = statuses.get(userId);
			if (status == null) {
				status = new UserStatus();
				statuses.put(userId, status);
			}
			return status;
		}
	}

	public static class UserStatus implements Serializable, IsSerializable {

		private Set<String> selectedEntitysIds = new HashSet<String>();
		private boolean online;

		public Set<String> getSelectedEntitysIds() {
			return selectedEntitysIds;
		}

		public boolean isOnline() {
			return online;
		}

	}

}
