package scrum.client.sprint;

import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.Composite;

public class TaskListWidget extends Composite {

	private BlockListWidget<TaskWidget> list = new BlockListWidget<TaskWidget>();
	private SprintStoryWidget sprintStoryWidget;

	public TaskListWidget(SprintStoryWidget storyWidget) {
		this.sprintStoryWidget = storyWidget;
		list.setController(new BlockListController<TaskWidget>() {

			@Override
			public void dataChanged(TaskWidget block) {
				sprintStoryWidget.taskDataChanged();
			}
		});

		initWidget(list);
		update(null);
	}

	public void update(Task selectedTask) {
		list.clear();
		TaskWidget select = null;
		for (Task task : sprintStoryWidget.getItem().getTasks()) {

			// taskListWidget setzen, um es spaeter wieder ansprechen zu koennen
			task.setTaskListWidget(this);

			TaskWidget block = new TaskWidget(task);
			if (task == selectedTask) select = block;
			list.addBlock(block);
		}
		if (select != null) list.selectBlock(select);
	}

}
