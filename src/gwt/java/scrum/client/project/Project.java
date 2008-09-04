package scrum.client.project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.client.Client;
import scrum.client.admin.User;
import scrum.client.common.AEntity;
import scrum.client.impediments.Impediment;

public class Project extends AEntity {

	public String label;
	public String masterId; // scrum master
	public String ownerId; // product owner
	public Set<String> participantsIds = new HashSet<String>(); // team
	public Set<String> impedimentsIds = new HashSet<String>();

	public User getMaster() {
		return Client.getUser(masterId);
	}

	public User getOwner() {
		return Client.getUser(ownerId);
	}

	public List<User> getParticipants() {
		return Client.getUsers(participantsIds);
	}

	public List<Impediment> getImpediments() {
		return Client.getImpediments(impedimentsIds);
	}

}
