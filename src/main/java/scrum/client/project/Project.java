package scrum.client.project;

import java.util.List;
import java.util.Map;

import scrum.client.impediments.Impediment;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

public class Project extends GProject {

	private static final String effortUnit = "StoryPoints";

	public Project() {}

	public Project(Map data) {
		super(data);
	}

	public String getEffortUnit() {
		return effortUnit;
	}

	public Impediment createNewImpediment() {
		Impediment impediment = new Impediment(this);
		getDao().createImpediment(impediment);
		return impediment;
	}

	public void deleteImpediment(Impediment impediment) {
		getDao().deleteImpediment(impediment);
	}

	public List<Impediment> getImpediments() {
		return getDao().getImpedimentsByProject(this);
	}

	public BacklogItem createNewBacklogItem() {
		BacklogItem item = new BacklogItem(this);
		getDao().createBacklogItem(item);
		return item;
	}

	public void deleteBacklogItem(BacklogItem item) {
		getDao().deleteBacklogItem(item);
	}

	public List<BacklogItem> getBacklogItems() {
		return getDao().getBacklogItemsByProject(this);
	}

	public Sprint createNewSprint() {
		Sprint sprint = new Sprint(this, "New Sprint");
		getDao().createSprint(sprint);
		return sprint;
	}

	public List<Sprint> getSprints() {
		return getDao().getSprintsByProject(this);
	}

	public boolean deleteTask(Task task) {
		for (BacklogItem backlogItem : getBacklogItems()) {
			boolean b = backlogItem.getTasks().remove(task);
			if (b) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
