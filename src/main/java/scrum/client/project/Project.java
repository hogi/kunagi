package scrum.client.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.impediments.Impediment;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

public class Project extends GProject {

	private static final String effortUnit = "StoryPoints";

	private Set<User> participants;
	private User master;
	private User owner;
	private Set<User> team;
	private List<Impediment> impediments = new ArrayList<Impediment>();
	private List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();
	private List<Sprint> sprints = new ArrayList<Sprint>();

	public Project() {
		createOnServer();
	}

	public Project(Map data) {
		super(data);
	}

	public String getEffortUnit() {
		return effortUnit;
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
		Impediment impediment = new Impediment(this);
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

	public void setImpediments(List<Impediment> impediments) {
		this.impediments = impediments;
	}

	public BacklogItem createNewBacklogItem() {
		BacklogItem item = new BacklogItem();
		backlogItems.add(item);
		return item;
	}

	public void deleteBacklogItem(BacklogItem item) {
		backlogItems.remove(item);
	}

	public List<BacklogItem> getBacklogItems() {
		return backlogItems;
	}

	public void setBacklogItems(List<BacklogItem> backlogItems) {
		this.backlogItems = backlogItems;
	}

	public Sprint createNewSprint(String label) {
		Sprint sprint = new Sprint(label);
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
			boolean b = backlogItem.getTasks().remove(task);
			if (b) return true;
		}
		return false;
	}

}
