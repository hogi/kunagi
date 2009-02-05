package scrum.client.common;

import java.util.HashMap;
import java.util.Map;

import scrum.client.Dao;
import scrum.client.ScrumGwtApplication;

/**
 * Base class for entities.
 */
public abstract class AGwtEntity {

	private String id;
	private boolean inCreation;

	public abstract String getEntityType();

	public AGwtEntity() {
		this.id = ScrumGwtApplication.get().getNewEntityId();
		inCreation = true;
	}

	public AGwtEntity(Map data) {
		this.id = (String) data.get("id");
	}

	public final String getId() {
		return id;
	}

	void setCreated() {
		this.inCreation = false;
	}

	protected final void propertyChanged(String property, Object value) {
		if (inCreation) return;
		if (value instanceof Date) value = value.toString();
		getDao().entityPropertyChanged(this, property, value);
	}

	public void storeProperties(Map properties) {
		properties.put("id", getId());
	}

	public Map createPropertiesMap() {
		Map properties = new HashMap();
		storeProperties(properties);
		return properties;
	}

	@Override
	public final boolean equals(Object obj) {
		return id.equals(((AGwtEntity) obj).id);
	}

	@Override
	public String toString() {
		return getId();
	}

	// --- helper ---

	protected final Dao getDao() {
		return ScrumGwtApplication.get().getDao();
	}

	protected final String toString(Integer value) {
		return value == null ? null : value.toString();
	}

	protected final String toString(Boolean value) {
		return value == null ? null : value.toString();
	}

	protected final boolean equals(Object a, Object b) {
		if (a == null && b == null) return true;
		if (a == null && b != null) return false;
		if (a != null && b == null) return false;
		return a.equals(b);
	}

	protected final boolean equals(String id, AGwtEntity entity) {
		if (id == null && entity == null) return true;
		if (id == null && entity != null) return false;
		if (id != null && entity == null) return false;
		return id.equals(entity.getId());
	}

}
