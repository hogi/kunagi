package scrum.client.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for entities.
 */
public class AEntity {

	private String id;

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

	public static final String ID = "id";

	private Map<String, String> properties = new HashMap<String, String>();

	protected final String getProperty(String name) {
		return properties.get(name);
	}

	protected final boolean getPropertyAsBool(String name) {
		String s = getProperty(name);
		if (s == null) return false;
		return s.equals("true");
	}

	protected final void setProperty(String name, String value) {
		properties.put(name, value);
	}

	protected final void setProperty(String name, boolean value) {
		setProperty(name, value ? "true" : "false");
	}

	protected final void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
