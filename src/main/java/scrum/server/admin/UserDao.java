package scrum.server.admin;

import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.logging.Log;

public class UserDao extends GUserDao {

	private static Log log = Log.get(UserDao.class);

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
		return postUser(name, scrum.client.admin.User.INITIAL_PASSWORD);
	}

	@Override
	public User newEntityInstance() {
		User user = super.newEntityInstance();
		user.setPassword("geheim");
		user.setRegistrationDateAndTime(DateAndTime.now());
		return user;
	}

	// --- test data ---

	public User getTestUser(String name) {
		User user = getUserByName(name);
		if (user == null) user = postUser(name);
		return user;
	}

}
