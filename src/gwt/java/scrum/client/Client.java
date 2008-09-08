package scrum.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import scrum.client.admin.User;
import scrum.client.impediments.Impediment;
import scrum.client.project.Project;

public class Client {

	private static int lastGeneratedEntityId;
	public static List<User> users = new ArrayList<User>();
	public static User user; // current user

	public static List<Project> projects = new ArrayList<Project>();
	public static Project project; // current project

	public static List<Impediment> impediments = new ArrayList<Impediment>();

	// --- project ---

	public static void requestUpdateProjects() {
		// dummy objects. TODO call server

		Project p1 = createProject(users.get(1));
		p1.label = "nuke the moon";
		p1.ownerId = users.get(0).id;
		p1.participantsIds.add(users.get(0).id);
		p1.participantsIds.add(users.get(1).id);

		Project p2 = createProject(users.get(0));
		p2.label = "ultimate internet virus";
		p2.ownerId = users.get(1).id;
		p2.participantsIds.add(users.get(0).id);
		p2.participantsIds.add(users.get(1).id);
		p2.participantsIds.add(users.get(2).id);
	}

	public static Project createProject(User master) {
		Project project = new Project();
		project.id = generateEntityId();
		project.label = "New Project";
		project.masterId = master.id;
		projects.add(project);
		return project;
	}

	// --- impediment ---

	public static void requestUpdateImpediments() {
		// dummy objects. TODO call server

		project = projects.get(0);

		Impediment pain = createImpediment();
		pain.label = "Pain in the ass";
		pain.description = "With pain in the ass, working on software is hard.";

		Impediment apocalypse = createImpediment();
		apocalypse.label = "Apocalypse";
		apocalypse.description = "When end of days comes, will our software survive?";

		Impediment lawsuit = createImpediment();
		lawsuit.label = "Software Patent Lawsuit";
		lawsuit.description = "What to do when we get sued for using someones intellectual property?";
	}

	public static Impediment createImpediment() {
		Impediment impediment = new Impediment();
		impediment.id = generateEntityId();
		project.impedimentsIds.add(impediment.id);
		impediment.label = "New Impediment";
		impediment.description = "Description...";
		impediments.add(impediment);
		return impediment;
	}

	public static void deleteImpediment(Impediment impediment) {
		project.impedimentsIds.remove(impediment.id);
		impediments.remove(impediment);
	}

	public static Impediment getImpediment(String id) {
		for (Impediment impediment : impediments) {
			if (id.equals(impediment.id)) return impediment;
		}
		return null;
	}

	public static List<Impediment> getImpediments(Collection<String> ids) {
		List<Impediment> ret = new ArrayList<Impediment>();
		for (String id : ids) {
			Impediment impediment = getImpediment(id);
			if (impediment == null) throw new RuntimeException("Impediment does not exist: " + id);
			ret.add(impediment);
		}
		return ret;
	}

	// --- user ---

	public static void requestUpdateUsers() {
		// dummy objects. TODO call server
		createUser("duke");
		createUser("cartman");
		createUser("jesus");
	}

	public static User createUser(String name) {
		User user = new User();
		user.id = generateEntityId();
		user.name = name;
		users.add(user);
		return user;
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

	// --- common stuff ---

	private static String generateEntityId() {
		return String.valueOf(++lastGeneratedEntityId);
	}
}
