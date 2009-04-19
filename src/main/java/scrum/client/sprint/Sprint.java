package scrum.client.sprint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scrum.client.admin.User;
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

	public List<Task> getTasks(User user) {
		List<Task> ret = new ArrayList<Task>();
		for (Requirement requirement : getRequirements()) {
			for (Task task : requirement.getTasks()) {
				if (user == null) {
					if (!task.isOwnerSet()) {
						ret.add(task);
					}
				} else {
					if (task.isOwner(user)) {
						ret.add(task);
					}
				}
			}
		}
		return ret;
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
