package scrum.client.admin;

import scrum.client.common.AEntity;

public class User extends AEntity {

	private String name;

	public User(String id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(User user) {
		if (user == null) return false;
		return getId().equals(user.getId());
	}

}
