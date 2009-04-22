package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WhiteboardWidget extends AWidget {

	private Grid grid;

	private Map<Requirement, TaskListWidget> openTasks;
	private Map<Requirement, TaskListWidget> ownedTasks;
	private Map<Requirement, TaskListWidget> closedTasks;

	@Override
	protected Widget onInitialization() {
		openTasks = new HashMap<Requirement, TaskListWidget>();
		ownedTasks = new HashMap<Requirement, TaskListWidget>();
		closedTasks = new HashMap<Requirement, TaskListWidget>();

		grid = new Grid();
		return grid;
	}

	@Override
	protected void onUpdate() {
		List<Requirement> requirements = ScrumGwtApplication.get().getProject().getCurrentSprint().getRequirements();

		grid.resize(requirements.size(), 4);

		for (Requirement requirement : requirements) {
			openTasks.put(requirement, new TaskListWidget("open"));
			ownedTasks.put(requirement, new TaskListWidget("owned"));
			closedTasks.put(requirement, new TaskListWidget("closed"));
		}

		Grid grid = new Grid(requirements.size(), 4);
		for (int i = 0; i < requirements.size(); i++) {
			Requirement requirement = requirements.get(i);

			Collection<Task> openTaskList = new ArrayList<Task>();
			Collection<Task> ownedTaskList = new ArrayList<Task>();
			Collection<Task> closedTaskList = new ArrayList<Task>();
			for (Task task : requirement.getTasks()) {
				if (task.isDone())
					closedTaskList.add(task);
				else if (task.isOwnerSet())
					ownedTaskList.add(task);
				else openTaskList.add(task);
			}

			openTasks.get(requirement).setTasks(openTaskList);
			ownedTasks.get(requirement).setTasks(ownedTaskList);
			closedTasks.get(requirement).setTasks(closedTaskList);

			grid.setWidget(i, 0, new Label(requirement.getLabel()));
			grid.setWidget(i, 1, openTasks.get(requirement));
			grid.setWidget(i, 2, ownedTasks.get(requirement));
			grid.setWidget(i, 3, closedTasks.get(requirement));
		}
	}

	public static WhiteboardWidget get() {
		return WorkareaWidget.get().getWhiteboard();
	}

}
