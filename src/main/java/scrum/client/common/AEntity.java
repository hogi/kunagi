package scrum.client.common;

import java.util.Map;

import scrum.client.ScrumGwtApplication;

/**
 * Base class for entities.
 */
public class AEntity {

	private String id;

	public AEntity() {}

	public AEntity(Map data) {
		this.id = (String) data.get("id");
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	protected final void propertyChanged(String property, String value) {
		ScrumGwtApplication.changeProperty(getId(), property, value);
	}

	@Override
	public final boolean equals(Object obj) {
		return id.equals(((AEntity) obj).id);
	}

	@Override
	public String toString() {
		return getId();
	}

	// --- helper ---

	protected String toString(Integer value) {
		return value == null ? null : value.toString();
	}

	protected String toString(Boolean value) {
		return value == null ? null : value.toString();
	}

}
