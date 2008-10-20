package scrum.client.admin;

import scrum.client.common.AEntity;

public class User extends AEntity {

	public static final String NAME = "name";

	public User(String id, String name) {
		super(id);
		setName(name);
	}

	public void setName(String name) {
		setProperty(NAME, name);
	}

	public String getName() {
		return getProperty(NAME);
	}

	public boolean equals(User user) {
		if (user == null) return false;
		return getId().equals(user.getId());
	}

}
