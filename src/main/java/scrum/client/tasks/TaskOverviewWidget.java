package scrum.client.tasks;

import java.util.HashMap;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class TaskOverviewWidget extends AScrumWidget implements TaskBlockContainer {

	private TaskListWidget myTasks;
	private TaskListWidget unownedTasks;
	private Map<User, TaskListWidget> ownedTasks;
	private BlockListSelectionManager selectionManager;

	@Override
	protected Widget onInitialization() {
		selectionManager = new BlockListSelectionManager();
		myTasks = new TaskListWidget(this);
		unownedTasks = new TaskListWidget(this);
		ownedTasks = new HashMap<User, TaskListWidget>();
		for (User user : getCurrentProject().getTeamMembers()) {
			if (user == getCurrentUser()) continue;
			TaskListWidget list = new TaskListWidget(this);
			ownedTasks.put(user, list);
		}

		PagePanel page = new PagePanel();
		page.addHeader("My Tasks");
		page.addSection(myTasks);
		page.addHeader("Tasks without Owner");
		page.addSection(unownedTasks);
		for (Map.Entry<User, TaskListWidget> entry : ownedTasks.entrySet()) {
			page.addHeader(entry.getKey().getName() + "'s Tasks");
			page.addSection(entry.getValue());
		}
		return page;
	}

	@Override
	protected void onUpdate() {
		Sprint currentSprint = getCurrentProject().getCurrentSprint();
		myTasks.setTasks(currentSprint.getTasks(getCurrentUser()));

		unownedTasks.setTasks(currentSprint.getTasks(null));

		for (Map.Entry<User, TaskListWidget> entry : ownedTasks.entrySet()) {
			entry.getValue().setTasks(currentSprint.getTasks(entry.getKey()));
		}
	}

	public BlockListSelectionManager getSelectionManager() {
		return selectionManager;
	}

	public void selectTask(Task task) {
		selectionManager.select(task);
	}

	public boolean isShowOwner() {
		return false;
	}

	public boolean isShowRequirement() {
		return true;
	}

	public boolean isWideMode() {
		return true;
	}

}
