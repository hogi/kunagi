package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.Collection;

import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskListWidget extends AWidget {

	private String title;
	private GroupWidget panel;
	private BlockListWidget<Task> list;

	private TaskBlockContainer container;

	public TaskListWidget(String title, TaskBlockContainer container) {
		this(container);
		this.title = title;
	}

	public TaskListWidget(TaskBlockContainer container) {
		this.container = container;
	}

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Task>(new TaskBlock.TaskBlockFactory(container));
		list.setSelectionManager(container.getSelectionManager());

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
		list.selectObject(task);
	}

	public void setTasks(Collection<Task> tasks) {
		initialize();
		list.setBlocks(tasks);
		update();
	}

}
