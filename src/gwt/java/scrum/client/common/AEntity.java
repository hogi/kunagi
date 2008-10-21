package scrum.client.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for entities.
 */
public class AEntity {

	public static final String ID = "id";

	private Map<String, String> properties = new HashMap<String, String>();

	public AEntity(String id) {
		setProperty(ID, id);
	}

	public AEntity() {

	}

	public String getId() {
		return getProperty(ID);
	}

	@Override
	public String toString() {
		return getId();
	}

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
