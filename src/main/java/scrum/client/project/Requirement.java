package scrum.client.project;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

public class Requirement extends GRequirement {

	public static final String INIT_LABEL = "New Requirement";

	public Requirement(Project project) {
		setProject(project);
		setLabel(INIT_LABEL);
	}

	public Requirement(Map data) {
		super(data);
	}

	/**
	 * No tasks created yet.
	 */
	public boolean isPlanned() {
		return !getTasks().isEmpty();
	}

	/**
	 * All tasks are done. Not closed yet.
	 */
	public boolean isDone() {
		Collection<Task> tasks = getTasks();
		if (tasks.isEmpty()) return false;
		for (Task task : tasks) {
			if (!task.isDone()) return false;
		}
		return true;
	}

	/**
	 * Summary to show in the product backlog.
	 */
	public String getProductBacklogSummary() {
		if (isClosed()) return "Closed.";
		if (isDone()) return "Done. Test required.";
		if (getEstimatedWork() == null) return "No effort estimated.";
		Sprint sprint = getSprint();
		if (sprint == null) return getEffortString() + " to do. No sprint assigned.";
		return getEffortString() + " to do in sprint " + sprint.getLabel() + ".";
	}

	/**
	 * Summary to show in the sprint backlog.
	 */
	public String getSprintBacklogSummary() {
		if (isClosed()) return "Closed.";
		if (!isPlanned()) return "Not planned yet.";
		if (isDone()) return "Done. Test required.";
		int taskCount = 0;
		int openTaskCount = 0;
		int effort = 0;
		for (Task task : getTasks()) {
			taskCount++;
			if (!task.isDone()) {
				openTaskCount++;
				effort += task.getRemainingWork();
			}
		}
		return openTaskCount + " of " + taskCount + " Tasks open. About " + effort + " hours to do.";
	}

	public String getEffortString() {
		if (getEstimatedWork() == null) return null;
		return getEstimatedWork() + " " + ScrumGwtApplication.get().getProject().getEffortUnit();
	}

	public Integer getTaskEffortSum() {
		Integer sum = null;
		for (Task t : getTasks()) {
			Integer effort = t.getRemainingWork();
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

	public List<Task> getTasks() {
		return getDao().getTasksByRequirement(this);
	}

	public Task createNewTask() {
		Task task = new Task(this);
		getDao().createTask(task);
		return task;
	}

	public void deleteTask(Task task) {
		getDao().deleteTask(task);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
