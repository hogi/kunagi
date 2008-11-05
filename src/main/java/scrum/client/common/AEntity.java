package scrum.client.common;

import java.util.Map;

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

	@Override
	public final boolean equals(Object obj) {
		return id.equals(((AEntity) obj).id);
	}

	@Override
	public String toString() {
		return getId();
	}

}
