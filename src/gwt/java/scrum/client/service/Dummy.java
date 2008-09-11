package scrum.client.service;

import java.util.HashSet;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class Dummy {

	public static User duke;
	public static User cartman;
	public static User satan;
	public static Set<User> users;

	public static Project moon;

	static {
		users = new HashSet<User>();
		duke = new User(EntityIdGenerator.generateId(), "duke");
		users.add(duke);
		cartman = new User(EntityIdGenerator.generateId(), "cartman");
		users.add(cartman);
		satan = new User(EntityIdGenerator.generateId(), "satan");
		users.add(satan);

		moon = new Project(EntityIdGenerator.generateId(), "Ultimate Virus", cartman, satan, users);
		moon.createNewImpediment().setLabel("Apocalypse").setDescription(
			"When end of days comes, will our software survive?");
		moon.createNewImpediment().setLabel("Software patent lawsuit").setDescription(
			"What to do when we get sued for using someones intellectual property?");
		moon.createNewImpediment().setLabel("Communication croblem").setDescription("Satan won't speak with Cartman")
				.setSolution("Force satan to speak with Cartman").setSolved(true);
		moon.createNewImpediment().setLabel("Out of stereoids").setDescription(
			"In two weeks Duke runs out of stereoids.").setSolution("Buy more stereoids.").setSolved(true);
		moon.createNewBacklogItem().setLabel("Reproduce");
		moon.createNewBacklogItem().setLabel("Destroy operating system");
		moon.createNewBacklogItem().setLabel("Destroy Hardware");
		moon.createNewBacklogItem().setLabel("Destroy connected Hardware (USB)");
		moon.createNewBacklogItem().setLabel("Destroy wireless Hardware (WLAN, Bluetooth)");
		moon.createNewBacklogItem().setLabel("Destroy user");

	}

}
