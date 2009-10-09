package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.HashMap;
import java.util.Map;

import scrum.client.ComponentManager;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskOverviewWidget extends AWidget implements TaskBlockContainer {

	private TaskListWidget myTasks;
	private TaskListWidget unownedTasks;
	private Map<User, TaskListWidget> ownedTasks;
	private BlockListSelectionManager selectionManager;

	@Override
	protected Widget onInitialization() {
		selectionManager = new BlockListSelectionManager();
		myTasks = new TaskListWidget("My tasks", this);
		unownedTasks = new TaskListWidget("Tasks without owner", this);
		ownedTasks = new HashMap<User, TaskListWidget>();
		for (User user : ScrumGwtApplication.get().getProject().getTeamMembers()) {
			if (user == ScrumGwtApplication.get().getUser()) continue;
			TaskListWidget list = new TaskListWidget(user.getName() + "'s Tasks", this);
			ownedTasks.put(user, list);
		}

		FlowPanel superPanel = new FlowPanel();
		superPanel.add(myTasks);
		superPanel.add(unownedTasks);

		for (TaskListWidget list : ownedTasks.values()) {
			superPanel.add(list);
		}

		return superPanel;
	}

	@Override
	protected void onUpdate() {
		Sprint currentSprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		myTasks.setTasks(currentSprint.getTasks(ScrumGwtApplication.get().getUser()));

		unownedTasks.setTasks(currentSprint.getTasks(null));

		for (Map.Entry<User, TaskListWidget> entry : ownedTasks.entrySet()) {
			entry.getValue().setTasks(currentSprint.getTasks(entry.getKey()));
		}
	}

	public static TaskOverviewWidget get() {
		return ComponentManager.get().getProjectContext().getTaskOverview();
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

}
