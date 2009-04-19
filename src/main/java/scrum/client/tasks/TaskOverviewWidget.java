package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.HashMap;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskOverviewWidget extends AWidget {

	private TaskListWidget myTasks;

	private TaskListWidget unownedTasks;

	private Map<User, TaskListWidget> ownedTasks;

	@Override
	protected Widget onInitialization() {
		myTasks = new TaskListWidget("My tasks");
		unownedTasks = new TaskListWidget("Tasks without owner");
		ownedTasks = new HashMap<User, TaskListWidget>();
		for (User user : ScrumGwtApplication.get().getProject().getTeamMembers()) {
			TaskListWidget list = new TaskListWidget(user.getName() + "'s Tasks");
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
		return WorkareaWidget.get().getTaskOverview();
	}

}
