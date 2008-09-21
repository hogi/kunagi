package scrum.client.project;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AEntity;
import scrum.client.project.task.Task;
import scrum.client.service.EntityIdGenerator;
import scrum.client.service.Service;

public class BacklogItem extends AEntity {

	private String label;
	private String description;
	private String testDescription;
	private Integer effort;
	private boolean done;
	private List<Task> taskList = new ArrayList<Task>();

	public BacklogItem(String id, String label) {
		super(id);
		this.label = label;
	}

	public Integer getEffort() {
		return effort;
	}

	public BacklogItem setEffort(Integer effort) {
		this.effort = effort;
		return this;
	}

	public String getEffortString() {
		if (effort == null) return null;
		return effort + " " + Service.getProject().getEffortUnit();
	}

	public String getLabel() {
		return label;
	}

	public BacklogItem setLabel(String label) {
		this.label = label;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public BacklogItem setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public BacklogItem setTestDescription(String test) {
		this.testDescription = test;
		return this;
	}

	public boolean isDone() {
		return done;
	}

	public BacklogItem setDone(boolean done) {
		this.done = done;
		return this;
	}

	public String getSummary() {
		if (done) return "Done.";
		if (effort == null) return "No effort estimated.";
		return getEffortString() + " to do.";
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public Task createNewTask() {
		Task task = new Task(EntityIdGenerator.generateId());
		taskList.add(task);
		return task;
	}

}
