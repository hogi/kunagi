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

	public Integer getBurnedWorkInClosedTasks() {
		Integer sum = null;
		for (Requirement s : getRequirements()) {
			Integer work = s.getBurnedWorkInClosedTasks();
			if (work != null) {
				if (sum == null) {
					sum = work;
				} else {
					sum += work;
				}
			}
		}
		return sum;
	}

	public Integer getBurnedWorkInClaimedTasks() {
		Integer sum = null;
		for (Requirement s : getRequirements()) {
			Integer work = s.getBurnedWorkInClaimedTasks();
			if (work != null) {
				if (sum == null) {
					sum = work;
				} else {
					sum += work;
				}
			}
		}
		return sum;
	}

	public Integer getRemainingWorkInClaimedTasks() {
		Integer sum = null;
		for (Requirement s : getRequirements()) {
			Integer work = s.getRemainingWorkInClaimedTasks();
			if (work != null) {
				if (sum == null) {
					sum = work;
				} else {
					sum += work;
				}
			}
		}
		return sum;
	}

	public Integer getRemainingWorkInUnclaimedTasks() {
		Integer sum = null;
		for (Requirement s : getRequirements()) {
			Integer effort = s.getRemainingWorkInUnclaimedTasks();
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

	public Integer getRemainingWork() {
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

	public String getRemainingWorkAsString() {
		Integer sum = getRemainingWork();
		if (sum != null) return sum + " hours";
		return "unknown";
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
