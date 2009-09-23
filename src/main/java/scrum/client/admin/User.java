package scrum.client.admin;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumGwtApplication;

public class User extends GUser {

	public static final String INITIAL_NAME = "newuser";
	public static final String INITIAL_PASSWORD = "geheim";

	public User() {
		setName(INITIAL_NAME);
	}

	public User(Map data) {
		super(data);
	}

	public ProjectUserConfig getProjectConfig() {
		return ScrumGwtApplication.get().getProject().getUserConfig(this);
	}

	public int compareTo(User u) {
		return getName().compareTo(u.getName());
	}

	@Override
	public String toString() {
		return getName();
	}

	public static final Comparator<User> NAME_COMPARATOR = new Comparator<User>() {

		public int compare(User a, User b) {
			return a.getName().compareTo(b.getName());
		}
	};

}
