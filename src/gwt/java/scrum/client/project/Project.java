package scrum.client.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.common.AEntity;
import scrum.client.impediments.Impediment;
import scrum.client.project.task.Task;
import scrum.client.service.EntityIdGenerator;
import scrum.client.service.ServerData;
import scrum.client.sprint.Sprint;

public class Project extends AEntity {

	public static final String LABEL = "label";

	private String effortUnit = "StoryPoints";

	private Set<User> participants;
	private User master;
	private User owner;
	private Set<User> team;
	private List<Impediment> impediments = new ArrayList<Impediment>();
	private List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();
	private List<Sprint> sprints = new ArrayList<Sprint>();

	public Project(String id, String label, User master, User owner, Set<User> team) {
		super(id);
		setProperty(LABEL, label);
		this.master = master;
		this.owner = owner;
		this.team = team;

		this.participants = new HashSet<User>();
		this.participants.addAll(team);
		this.participants.add(owner);
		this.participants.add(master);
	}

	public void setEffortUnit(String effortUnit) {
		this.effortUnit = effortUnit;
	}

	public String getEffortUnit() {
		return effortUnit;
	}

	public String getLabel() {
		return getProperty(LABEL);
	}

	public void setLabel(String label) {
		setProperty(LABEL, label);
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

	public Set<User> getTeam() {
		return team;
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

	public Sprint getCurrentSprint() {
		for (Sprint sprint : sprints) {
			if (sprint.getState().equals(Sprint.State.Development)) return sprint;
		}
		return null;
	}

	// TODO permission? s
	public boolean deleteTask(Task task) {
		for (BacklogItem backlogItem : backlogItems) {
			boolean b = backlogItem.getTaskList().remove(task);
			if (b) return true;
		}
		return false;
	}

	public void update(ServerData data) {
		setProperties(data.project);
	}

}
