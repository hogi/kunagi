package scrum.client.admin;

import ilarkesto.core.scope.Scope;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumScopeManager;
import scrum.client.collaboration.UsersStatus;

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
		return ScrumScopeManager.getProject().getUserConfig(this);
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

	public transient static final Comparator<User> ONLINE_OFFLINE_COMPARATOR = new Comparator<User>() {

		public int compare(User a, User b) {
			UsersStatus usersStatus = Scope.get().getComponent(UsersStatus.class);
			boolean aOnline = usersStatus.isOnline(a);
			boolean bOnline = usersStatus.isOnline(b);
			if (aOnline == bOnline) return a.getName().compareTo(b.getName());
			if (aOnline) return -1;
			return 1;
		}
	};

}
