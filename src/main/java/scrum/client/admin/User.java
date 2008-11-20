package scrum.client.admin;

import java.util.Map;

public class User extends GUser {

	public User() {}

	public User(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getName();
	}

}
