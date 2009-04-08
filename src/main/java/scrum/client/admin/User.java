package scrum.client.admin;

import java.util.Map;

public class User extends GUser {

	public static final String INITIAL_NAME = "newuser";
	public static final String INITIAL_PASSWORD = "geheim";

	public User() {
		setName(INITIAL_NAME);
	}

	public User(Map data) {
		super(data);
	}

	public int compareTo(User u) {
		return getName().compareTo(u.getName());
	}

	@Override
	public String toString() {
		return getName();
	}

}
