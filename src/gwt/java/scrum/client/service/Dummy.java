package scrum.client.service;

import java.util.HashSet;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.project.BacklogItem;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;

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
		
		BacklogItem b1 = moon.createNewBacklogItem();
		b1.setLabel("Reproduce");

		BacklogItem b2 = moon.createNewBacklogItem();
		b2.setLabel("Destroy operating system");

		BacklogItem b3 = moon.createNewBacklogItem();
		b3.setLabel("Destroy Hardware");

		BacklogItem b4 = moon.createNewBacklogItem();
		b4.setLabel("Destroy connected Hardware (USB)");

		BacklogItem b5 = moon.createNewBacklogItem();
		b5.setLabel("Destroy wireless Hardware (WLAN, Bluetooth)");

		BacklogItem b6 = moon.createNewBacklogItem();
		b6.setLabel("Destroy user");
		
		Sprint s1 = moon.createNewSprint("Hardware Destroy Sprint");
		s1.setState(Sprint.State.Development);
		s1.getBacklogItems().add(b3);
		s1.getBacklogItems().add(b4);
		s1.getBacklogItems().add(b5);

	}

}
