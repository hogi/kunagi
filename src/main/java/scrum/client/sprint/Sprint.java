package scrum.client.sprint;

import java.util.List;
import java.util.Map;

import scrum.client.project.Project;
import scrum.client.project.Story;

public class Sprint extends GSprint {

	public Sprint(Project project, String label) {
		setProject(project);
		setLabel(label);
	}

	public Sprint(Map data) {
		super(data);
	}

	public List<Story> getStorys() {
		return getDao().getStorysBySprint(this);
	}

	public Integer getTaskEffortSum() {
		Integer sum = null;
		for (Story s : getStorys()) {
			Integer effort = s.getTaskEffortSum();
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
