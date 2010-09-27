package scrum.server.admin;

import ilarkesto.auth.OpenId;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.logging.Log;
import scrum.server.ScrumWebApplication;

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

	public User postUserWithDefaultPassword(String name) {
		return postUser(name, getDefaultPassword());
	}

	public User postUserWithOpenId(String openId) {
		String name = OpenId.cutUsername(openId);
		if (getUserByName(name) != null) name = openId;

		User user = newEntityInstance();
		user.setName(name);
		user.setOpenId(openId);
		saveEntity(user);
		log.info("User created:", user);
		return user;
	}

	@Override
	public User newEntityInstance() {
		User user = super.newEntityInstance();
		String password = getDefaultPassword();
		user.setPassword(password);
		user.setRegistrationDateAndTime(DateAndTime.now());
		return user;
	}

	private String getDefaultPassword() {
		return ScrumWebApplication.get().getSystemConfig().getDefaultUserPassword();
	}

	// --- test data ---

	public User getTestUser(String name) {
		User user = getUserByName(name);
		if (user == null) user = postUserWithDefaultPassword(name);
		return user;
	}

}
