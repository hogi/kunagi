package scrum.client.project;

import java.util.List;
import java.util.Map;

import scrum.client.impediments.Impediment;
import scrum.client.risks.Risk;
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

	public void deleteRisk(Risk risk) {
		getDao().deleteRisk(risk);
	}

	public List<Impediment> getImpediments() {
		return getDao().getImpedimentsByProject(this);
	}

	public List<Risk> getRisks() {
		return getDao().getRisksByProject(this);
	}

	public Risk createNewRisk() {
		Risk risk = new Risk(this);
		getDao().createRisk(risk);
		return risk;
	}

	public Requirement createNewRequirement() {
		Requirement item = new Requirement(this);
		getDao().createRequirement(item);
		return item;
	}

	public Attribute createNewAttribute() {
		Attribute item = new Attribute(this);
		getDao().createAttribute(item);
		return item;
	}

	public void deleteRequirement(Requirement item) {
		getDao().deleteRequirement(item);
	}

	public void deleteAttribute(Attribute item) {
		getDao().deleteAttribute(item);
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsByProject(this);
	}

	public List<Attribute> getAttributes() {
		return getDao().getAttributesByProject(this);
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
