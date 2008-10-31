package scrum.client.service;

public class EntityIdGenerator {

	private static int lastId;

	public static String generateId() {
		return String.valueOf(++lastId);
	}

}
