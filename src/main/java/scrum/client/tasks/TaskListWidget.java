package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskListWidget extends AWidget {

	private String title;
	private GroupWidget panel;
	private BlockListWidget<Task> list;

	public TaskListWidget(String title) {
		this.title = title;
	}

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Task>(TaskBlock.FACTORY);

		panel = new GroupWidget(title);
		panel.setContent(list);

		return panel;
	}

	@Override
	protected void onUpdate() {
		panel.update();
	}

}
