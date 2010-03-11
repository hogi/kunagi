package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.List;

import scrum.client.common.BlockListWidget;
import scrum.client.common.ElementPredicate;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskListWidget extends AWidget {

	private BlockListWidget<Task> list;
	private BlockListDropAction<Task> dropAction;

	private TaskBlockContainer container;

	public TaskListWidget(TaskBlockContainer container) {
		this(container, null);
	}

	public TaskListWidget(TaskBlockContainer container, BlockListDropAction<Task> dropAction) {
		this.container = container;
		this.dropAction = dropAction;
	}

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Task>(new TaskBlock.TaskBlockFactory(container), this.dropAction);
		list.setSelectionManager(container.getSelectionManager());
		list.setAutoSorter(Task.NUMBER_COMPARATOR);
		list.setMinHeight(100);
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
}
