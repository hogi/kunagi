package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.List;

import scrum.client.GenericPredicate;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.dnd.MoveDropAction;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskListWidget extends AWidget {

	private String title;
	private GroupWidget panel;
	private BlockListWidget<Task> list;
	private BlockListDropAction<Task> dropAction;

	private TaskBlockContainer container;

	public TaskListWidget(String title, TaskBlockContainer container) {
		this(container);
		this.title = title;
	}

	public TaskListWidget(TaskBlockContainer container) {
		this(container, new MoveDropAction());
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

		if (title == null) return list;

		panel = new GroupWidget(title);
		panel.setContent(list);

		return panel;
	}

	@Override
	protected void onUpdate() {
		if (panel != null) {
			panel.update();
		} else {
			list.update();
		}
	}

	public void selectTask(Task task) {
		list.extendObject(task);
	}

	public void setTasks(List<Task> tasks) {
		initialize();
		list.setObjects(tasks);
	}

	public void setTaskHighlighting(GenericPredicate<Task> predicate) {
		list.setTaskHighlighting(predicate);
	}

	public void clearTaskHighlighting() {
		list.clearTaskHighlighting();
	}
}
