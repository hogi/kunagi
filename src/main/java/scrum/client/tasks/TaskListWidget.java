package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import java.util.Collection;

import scrum.client.common.BlockListSelectionManager;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskListWidget extends AWidget {

	private String title;
	private GroupWidget panel;
	private BlockListWidget<Task> list;

	private BlockListSelectionManager selectionManager;

	public TaskListWidget(String title, BlockListSelectionManager selectionManager) {
		this(selectionManager);
		this.title = title;
	}

	public TaskListWidget(BlockListSelectionManager selectionManager) {
		this.selectionManager = selectionManager;
	}

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Task>(TaskBlock.FACTORY);
		list.setSelectionManager(selectionManager);

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

	public void setTasks(Collection<Task> tasks) {
		initialize();
		list.setBlocks(tasks);
		update();
	}

}
