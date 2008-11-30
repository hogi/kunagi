package scrum.client.sprint;

import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.Composite;

public class TaskListWidget extends Composite {

	private BlockListWidget<TaskWidget> list = new BlockListWidget<TaskWidget>();
	private SprintBacklogItemWidget backlogItemWidget;

	public TaskListWidget(SprintBacklogItemWidget backlogItemWidget) {
		this.backlogItemWidget = backlogItemWidget;
		list.setController(new BlockListController<TaskWidget>() {

			@Override
			public void dataChanged(TaskWidget block) {
				throw new RuntimeException("Not implemented.");
			}
		});

		initWidget(list);
		update(null);
	}

	public void update(Task selectedTask) {
		list.clear();
		TaskWidget select = null;
		for (Task task : backlogItemWidget.getItem().getTasks()) {

			// taskListWidget setzen, um es spaeter wieder ansprechen zu koennen
			task.setTaskListWidget(this);

			TaskWidget block = new TaskWidget(task);
			if (task == selectedTask) select = block;
			list.addBlock(block);
		}
		if (select != null) list.selectBlock(select);
	}

}
