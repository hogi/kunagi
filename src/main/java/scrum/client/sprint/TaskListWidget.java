package scrum.client.sprint;

import scrum.client.common.BlockListWidget;
import scrum.client.project.BacklogItem;

import com.google.gwt.user.client.ui.Composite;

public class TaskListWidget extends Composite {

	private BlockListWidget list = new BlockListWidget();
	private BacklogItem backlogItem;

	public TaskListWidget(BacklogItem backlogItem) {
		this.backlogItem = backlogItem;

		initWidget(list);
		update();
	}

	public void update() {
		list.clear();
		for (Task task : backlogItem.getTasks()) {

			// taskListWidget setzen, um es spaeter wieder ansprechen zu koennen
			task.setTaskListWidget(this);

			list.addBlock(new TaskWidget(task));
		}
	}

}
