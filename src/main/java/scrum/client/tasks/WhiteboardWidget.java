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
		grid.setWidth("100%");
		return grid;
	}

	@Override
	protected void onUpdate() {
		List<Requirement> requirements = ScrumGwtApplication.get().getProject().getCurrentSprint().getRequirements();

		grid.resize(requirements.size() + 1, 4);

		for (Requirement requirement : requirements) {
			openTasks.put(requirement, new TaskListWidget());
			ownedTasks.put(requirement, new TaskListWidget());
			closedTasks.put(requirement, new TaskListWidget());
		}

		grid.setWidget(0, 0, new Label("Requirement"));
		grid.setWidget(0, 1, new Label("Open"));
		grid.setWidget(0, 2, new Label("Owned"));
		grid.setWidget(0, 3, new Label("Done"));
		grid.getColumnFormatter().setWidth(0, "10%");
		grid.getColumnFormatter().setWidth(1, "30%");
		grid.getColumnFormatter().setWidth(2, "30%");
		grid.getColumnFormatter().setWidth(3, "30%");

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

			int row = i + 1;
			grid.setWidget(row, 0, new Label(requirement.getLabel()));
			grid.setWidget(row, 1, openTasks.get(requirement));
			grid.setWidget(row, 2, ownedTasks.get(requirement));
			grid.setWidget(row, 3, closedTasks.get(requirement));
		}
	}

	public static WhiteboardWidget get() {
		return WorkareaWidget.get().getWhiteboard();
	}

}
