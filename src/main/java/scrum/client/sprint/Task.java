package scrum.client.sprint;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.BacklogItem;

public class Task extends GTask {

	public static final String STATE_OPEN = "Open";
	public static final String STATE_OWNED = "Owned";
	public static final String STATE_FINISHED = "Finished";
	public static final String STATE_TEST = "Test";
	public static final String STATE_CLOSED = "Closed";

	private User owner;
	private String state;

	// Das TaskListWidget, in dem der Task liegt
	private TaskListWidget taskListWidget = null;

	public Task(BacklogItem backlogItem) {
		setBacklogItem(backlogItem);
		setLabel("New Task");
		state = STATE_OPEN;
	}

	public Task(Map data) {
		super(data);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	// kleiner Workaround, um an das TaskListWidget zu kommen
	public TaskListWidget getTaskListWidget() {
		return taskListWidget;
	}

	public void setTaskListWidget(TaskListWidget taskListWidget) {
		this.taskListWidget = taskListWidget;
	}

}
