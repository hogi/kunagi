package scrum.server.admin;

import ilarkesto.fp.Predicate;

public class UserDao extends GUserDao {

	@Override
	public User getUserByName(final String name) {
		return getEntity(new Predicate<User>() {

			public boolean test(User e) {
				return name.equals(e.getName());
			}
		});
	}

	@Override
	public User postUser(String name, String password) {
		User user = newEntityInstance();
		user.setName(name);
		user.setRealName(name);
		user.setPassword(password);
		saveEntity(user);
		return user;
	}

	public User postUser(String name) {
		return postUser(name, "geheim");
	}

	// --- test data ---

	public User getTestUser(String name) {
		User user = getUserByName(name);
		if (user == null) user = postUser(name);
		return user;
	}

}
