package scrum.client.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.EntityIdGenerator;
import scrum.client.sprint.Task;

public class BacklogItem extends GBacklogItem {

	public static final String INIT_LABEL = "New Backlog Item";

	private List<Task> tasks = new ArrayList<Task>();

	public BacklogItem(String id) {
		setId(id);
		setLabel(INIT_LABEL);
	}

	public BacklogItem(Map data) {
		super(data);
	}

	public String getEffortString() {
		if (getEffort() == null) return null;
		return getEffort() + " " + ScrumGwtApplication.getProject().getEffortUnit();
	}

	public String getSummary() {
		if (isDone()) return "Done.";
		if (getEffort() == null) return "No effort estimated.";
		return getEffortString() + " to do.";
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> taskList) {
		this.tasks = taskList;
	}

	public Task createNewTask() {
		Task task = new Task(EntityIdGenerator.generateId());
		tasks.add(task);
		return task;
	}

}
