package scrum.client.tasks;

import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ElementPredicate;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskListWidget extends AScrumWidget {

	private BlockListWidget<Task> list;
	private BlockListDropAction<Task> dropAction;

	private TaskBlockContainer container;
	private Requirement requirement;

	public TaskListWidget(Requirement requirement, TaskBlockContainer container) {
		this(requirement, container, null);
	}

	public TaskListWidget(Requirement requirement, TaskBlockContainer container, BlockListDropAction<Task> dropAction) {
		this.requirement = requirement;
		this.container = container;
		this.dropAction = dropAction;
	}

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Task>(new TaskBlock.TaskBlockFactory(container), this.dropAction);
		list.setSelectionManager(container.getSelectionManager());
		list.setMinHeight(100);
		list.setAutoSorter(requirement.getTasksOrderComparator());
		if (requirement.getProject().isTeamMember(getCurrentUser())) {
			list.setDndSorting(true);
			list.setMoveObserver(new MoveObserver());
		}
		return list;
	}

	public boolean selectTask(Task task) {
		if (!list.contains(task)) update();
		return list.showObject(task);
	}

	public void setTasks(List<Task> tasks) {
		initialize();
		list.setObjects(tasks);
	}

	public void setTaskHighlighting(ElementPredicate<Task> predicate) {
		list.setTaskHighlighting(predicate);
	}

	public void clearTaskHighlighting() {
		list.clearTaskHighlighting();
	}

	class MoveObserver implements Runnable {

		public void run() {
			List<Task> tasks = list.getObjects();
			requirement.updateTasksOrder(tasks);
			update();
		}

	}
}
