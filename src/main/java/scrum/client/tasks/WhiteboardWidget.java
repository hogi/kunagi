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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WhiteboardWidget extends AWidget {

	private Grid grid;
	private Label openLabel;
	private Label ownedLabel;
	private Label doneLabel;

	private Map<Requirement, TaskListWidget> openTasks;
	private Map<Requirement, TaskListWidget> ownedTasks;
	private Map<Requirement, TaskListWidget> closedTasks;

	@Override
	protected Widget onInitialization() {
		openLabel = new Label("Tasks without Owner");
		openLabel.setStyleName("WhiteboardWidget-columnLabel");
		ownedLabel = new Label("Owned Tasks");
		ownedLabel.setStyleName("WhiteboardWidget-columnLabel");
		doneLabel = new Label("Completed Tasks");
		doneLabel.setStyleName("WhiteboardWidget-columnLabel");

		openTasks = new HashMap<Requirement, TaskListWidget>();
		ownedTasks = new HashMap<Requirement, TaskListWidget>();
		closedTasks = new HashMap<Requirement, TaskListWidget>();

		grid = new Grid();
		grid.setWidth("100%");
		grid.setCellPadding(0);
		grid.setCellSpacing(0);
		return grid;
	}

	@Override
	protected void onUpdate() {
		List<Requirement> requirements = ScrumGwtApplication.get().getProject().getCurrentSprint().getRequirements();

		grid.resize((requirements.size() * 2) + 1, 3);

		for (Requirement requirement : requirements) {
			openTasks.put(requirement, new TaskListWidget());
			ownedTasks.put(requirement, new TaskListWidget());
			closedTasks.put(requirement, new TaskListWidget());
		}

		setWidget(0, 0, openLabel, "33%", "WhiteboardWidget-open");
		setWidget(0, 1, ownedLabel, "33%", "WhiteboardWidget-owned");
		setWidget(0, 2, doneLabel, "33%", "WhiteboardWidget-done");
		// grid.getColumnFormatter().setWidth(0, "1*");
		// grid.getColumnFormatter().setWidth(1, "1*");
		// grid.getColumnFormatter().setWidth(2, "1*");

		int row = 1;
		for (int i = 0; i < requirements.size(); i++) {
			Requirement requirement = requirements.get(i);

			Label label = new Label(requirement.getLabel());
			label.setStyleName("WhiteboardWidget-requirement-label");
			grid.setWidget(row, 0, label);
			grid.getCellFormatter().getElement(row, 0).setAttribute("colspan", "3");
			row++;

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

			// grid.setWidget(row, 0, new Label(requirement.getLabel()));
			setWidget(row, 0, openTasks.get(requirement), null, "WhiteboardWidget-open");
			setWidget(row, 1, ownedTasks.get(requirement), null, "WhiteboardWidget-owned");
			setWidget(row, 2, closedTasks.get(requirement), null, "WhiteboardWidget-done");

			row++;
		}
	}

	private void setWidget(int row, int col, Widget widget, String width, String className) {
		grid.setWidget(row, col, widget);
		if (width != null || className != null) {
			Element td = grid.getCellFormatter().getElement(row, col);
			if (width != null) td.setAttribute("width", width);
			if (className != null) td.setClassName(className);
		}
	}

	public static WhiteboardWidget get() {
		return WorkareaWidget.get().getWhiteboard();
	}

}
