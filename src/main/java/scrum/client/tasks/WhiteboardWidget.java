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

	private Map<Requirement, TaskListWidget> openTasks;
	private Map<Requirement, TaskListWidget> ownedTasks;
	private Map<Requirement, TaskListWidget> closedTasks;

	@Override
	protected Widget onInitialization() {
		openTasks = new HashMap<Requirement, TaskListWidget>();
		ownedTasks = new HashMap<Requirement, TaskListWidget>();
		closedTasks = new HashMap<Requirement, TaskListWidget>();

		List<Requirement> requirements = ScrumGwtApplication.get().getProject().getCurrentSprint().getRequirements();
		for (Requirement requirement : requirements) {
			TaskListWidget open = new TaskListWidget("open");
			TaskListWidget owned = new TaskListWidget("owned");
			TaskListWidget closed = new TaskListWidget("closed");

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

			open.setTasks(openTaskList);
			owned.setTasks(ownedTaskList);
			closed.setTasks(closedTaskList);

			openTasks.put(requirement, open);
			ownedTasks.put(requirement, owned);
			closedTasks.put(requirement, closed);
		}

		Grid grid = new Grid(requirements.size(), 4);
		for (Requirement requirement : requirements) {
			grid.add(new Label(requirement.getLabel()));
			grid.add(openTasks.get(requirement));
			grid.add(ownedTasks.get(requirement));
			grid.add(closedTasks.get(requirement));
		}

		return grid;
	}

	@Override
	protected void onUpdate() {
		List<Requirement> requirements = ScrumGwtApplication.get().getProject().getCurrentSprint().getRequirements();

		for (Requirement requirement : requirements) {
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
		}
	}

	public static WhiteboardWidget get() {
		return WorkareaWidget.get().getWhiteboard();
	}

}
