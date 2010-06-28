package scrum.server.admin;

import ilarkesto.core.logging.Log;
import ilarkesto.fp.Predicate;

public class UserDao extends GUserDao {

	private static Log log = Log.get(UserDao.class);

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
		return postUser(null, name, password);
	}

	public User postUser(String email, String name, String password) {
		User user = newEntityInstance();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		saveEntity(user);
		log.info("User created:", user);
		return user;
	}

	public User postUser(String name) {
		return postUser(name, "geheim");
	}

	@Override
	public User newEntityInstance() {
		User user = super.newEntityInstance();
		user.setPassword("geheim");
		return user;
	}

	// --- test data ---

	public User getTestUser(String name) {
		User user = getUserByName(name);
		if (user == null) user = postUser(name);
		return user;
	}

}
