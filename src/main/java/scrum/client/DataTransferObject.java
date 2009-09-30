package scrum.client;

import ilarkesto.gwt.client.ADataTransferObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataTransferObject extends ADataTransferObject implements Serializable, IsSerializable {

	public Set<String> onlineTeamMembersIds;
	public ApplicationInfo applicationInfo;
	public Map<String, Set<String>> usersSelectedEntitysIds;

	public synchronized void setUsersSelectedEntitiesIds(String userId, Set<String> entitiesIds) {
		if (usersSelectedEntitysIds == null) {
			usersSelectedEntitysIds = new HashMap<String, Set<String>>();
		}
		usersSelectedEntitysIds.put(userId, entitiesIds);
	}
}
