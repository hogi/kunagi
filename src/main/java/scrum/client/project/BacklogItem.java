package scrum.client.project;

import java.util.ArrayList;
import java.util.List;

import scrum.client.service.EntityIdGenerator;
import scrum.client.service.ScrumClient;
import scrum.client.sprint.Task;

public class BacklogItem extends GBacklogItem {

	private List<Task> tasks = new ArrayList<Task>();

	public BacklogItem(String id, String label) {
		setId(id);
		setLabel(label);
	}

	public String getEffortString() {
		if (getEffort() == null) return null;
		return getEffort() + " " + ScrumClient.getProject().getEffortUnit();
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
