package scrum.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Client {

	public static List<User> users = new ArrayList<User>();
	public static User user; // current user

	public static List<Project> projects = new ArrayList<Project>();
	public static Project project; // current project

	public static void requestUpdateUsers() {
		// dummy objects. TODO call server
		users = new ArrayList();

		User duke = new User();
		duke.id = "1";
		duke.name = "duke";
		users.add(duke);

		User cartman = new User();
		cartman.id = "2";
		cartman.name = "cartman";
		users.add(cartman);

		User jesus = new User();
		jesus.id = "666";
		jesus.name = "jesus";
		users.add(jesus);
	}

	public static void requestUpdateProjects() {
		// dummy objects. TODO call server
		projects = new ArrayList<Project>();

		Project p1 = new Project();
		p1.id = "1";
		p1.name = "nuke the moon";
		p1.ownerId = "1";
		p1.masterId = "666";
		p1.participantsIds.add("1");
		p1.participantsIds.add("666");
		projects.add(p1);

		Project p2 = new Project();
		p2.id = "2";
		p2.name = "ultimate internet virus";
		p2.ownerId = "2";
		p2.masterId = "1";
		p2.participantsIds.add("1");
		p2.participantsIds.add("2");
		p2.participantsIds.add("666");
		projects.add(p2);
	}

	public static User getUser(String id) {
		for (User user : users) {
			if (id.equals(user.id)) return user;
		}
		return null;
	}

	public static List<User> getUsers(Collection<String> ids) {
		List<User> ret = new ArrayList<User>();
		for (String id : ids) {
			User user = getUser(id);
			if (user == null) throw new RuntimeException("User does not exist: " + id);
			ret.add(user);
		}
		return ret;
	}

}
