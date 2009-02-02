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

	public Requirement createNewRequirement() {
		Requirement item = new Requirement(this);
		getDao().createRequirement(item);
		return item;
	}

	public void deleteRequirement(Requirement item) {
		getDao().deleteRequirement(item);
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsByProject(this);
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
		for (Requirement requirement : getRequirements()) {
			boolean b = requirement.getTasks().remove(task);
			if (b) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
