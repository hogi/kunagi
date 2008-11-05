package scrum.client.common;

public class EntityIdGenerator {

	private static int lastId;

	public static String generateId() {
		return String.valueOf(++lastId);
	}

}
