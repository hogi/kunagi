package scrum.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Project {

	public String id;
	public String name;
	public String masterId; // scrum master
	public String ownerId; // product owner
	public Set<String> participantsIds = new HashSet<String>(); // team

	public User getMaster() {
		return Client.getUser(masterId);
	}

	public User getOwner() {
		return Client.getUser(ownerId);
	}

	public List<User> getParticipants() {
		return Client.getUsers(participantsIds);
	}

}
