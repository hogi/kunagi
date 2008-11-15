package scrum.client.project;

import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.sprint.Task;

public class BacklogItem extends GBacklogItem {

	public static final String INIT_LABEL = "New Backlog Item";

	public BacklogItem(Project project) {
		setProject(project);
		setLabel(INIT_LABEL);
	}

	public BacklogItem(Map data) {
		super(data);
	}

	public String getEffortString() {
		if (getEffort() == null) return null;
		return getEffort() + " " + ScrumGwtApplication.get().getProject().getEffortUnit();
	}

	public String getSummary() {
		if (isDone()) return "Done.";
		if (getEffort() == null) return "No effort estimated.";
		return getEffortString() + " to do.";
	}

	public List<Task> getTasks() {
		return getDao().getTasksByBacklogItem(this);
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
