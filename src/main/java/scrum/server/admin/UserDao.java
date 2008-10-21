package scrum.server.admin;

import ilarkesto.auth.AUser;
import ilarkesto.fp.Predicate;

public class UserDao extends GUserDao {

	@Override
	public AUser getUserByName(final String name) {
		return getEntity(new Predicate<User>() {

			public boolean test(User e) {
				return name.equals(e.getName());
			}
		});
	}

	@Override
	public AUser postUser(String name, String password) {
		User user = new User();
		user.setName(name);
		user.setRealName(name);
		user.setPassword(password);
		return user;
	}

}
