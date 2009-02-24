package scrum.client.sprint;

import java.util.List;
import java.util.Map;

import scrum.client.project.Project;
import scrum.client.project.Requirement;

public class Sprint extends GSprint {

	public Sprint(Project project, String label) {
		setProject(project);
		setLabel(label);
	}

	public Sprint(Map data) {
		super(data);
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsBySprint(this);
	}

	public Integer getTaskEffortSum() {
		Integer sum = null;
		for (Requirement s : getRequirements()) {
			Integer effort = s.getRemainingWork();
			if (effort != null) {
				if (sum == null) {
					sum = effort;
				} else {
					sum += effort;
				}
			}
		}
		return sum;
	}

	public String getTaskEffortSumString() {
		Integer sum = getTaskEffortSum();
		if (sum != null) return sum + " hours";
		return "unknown";
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
