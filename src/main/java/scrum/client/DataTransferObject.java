package scrum.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is for transporting data from the scrum server to the GWT client.
 */
public class DataTransferObject implements Serializable {

	public String entityIdBase;
	public List<String> errors = new ArrayList<String>();

	private String userId;
	private Set<String> deletedEntities;
	private Map<String, Map> entities;

	public void setUserId(String user) {
		this.userId = user;
	}

	public String getUserId() {
		return userId;
	}

	public boolean isUserSet() {
		return userId != null;
	}

	public final boolean containsEntities() {
		return entities != null && !entities.isEmpty();
	}

	public final void addEntity(Map data) {
		if (entities == null) entities = new HashMap<String, Map>();
		entities.put((String) data.get("id"), data);
	}

	public final void addEntities(Collection<Map> dataList) {
		for (Map data : dataList)
			addEntity(data);
	}

	public final Collection<Map> getEntities() {
		return entities.values();
	}

	public final boolean containsDeletedEntities() {
		return deletedEntities != null && !deletedEntities.isEmpty();
	}

	public final void addDeletedEntity(String entityId) {
		if (deletedEntities == null) deletedEntities = new HashSet<String>();
		deletedEntities.add(entityId);
	}

	public final Set<String> getDeletedEntities() {
		return deletedEntities;
	}

}
