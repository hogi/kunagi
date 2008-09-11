package scrum.client.common;

/**
 * Base class for entities.
 */
public class AEntity {

	private String id;

	public AEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

}
