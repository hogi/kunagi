package scrum.client.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.common.AEntity;
import scrum.client.impediments.Impediment;
import scrum.client.service.EntityIdGenerator;
import scrum.client.sprint.Sprint;

public class Project extends AEntity {

	private String label;
	private User master; // scrum master
	private User owner; // product owner
	private Set<User> participants; // team
	private List<Impediment> impediments = new ArrayList<Impediment>();
	private List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();
	private List<Sprint> sprints = new ArrayList<Sprint>();
	
	public Project(String id, String label, User master, User owner, Set<User> participants) {
		super(id);
		this.label = label;
		this.master = master;
		this.owner = owner;
		this.participants = participants;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public User getMaster() {
		return master;
	}

	public User getOwner() {
		return owner;
	}

	public Collection<User> getParticipants() {
		return participants;
	}

	public Impediment createNewImpediment() {
		Impediment impediment = new Impediment(EntityIdGenerator.generateId(), "New impediment", "Description...");
		impediments.add(impediment);
		// TODO message to server
		return impediment;
	}

	public void deleteImpediment(Impediment impediment) {
		impediments.remove(impediment);
		// TODO message to server
	}

	public List<Impediment> getImpediments() {
		return impediments;
	}

	public BacklogItem createNewBacklogItem() {
		BacklogItem item = new BacklogItem(EntityIdGenerator.generateId(), "New item");
		backlogItems.add(item);
		return item;
	}

	public void deleteBacklogItem(BacklogItem item) {
		backlogItems.remove(item);
	}

	public List<BacklogItem> getBacklogItems() {
		return backlogItems;
	}
	
	public Sprint createNewSprint(String label) {
		Sprint sprint = new Sprint(EntityIdGenerator.generateId(), label);
		sprints.add(sprint);
		// TODO message to server
		return sprint;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}
	
}
