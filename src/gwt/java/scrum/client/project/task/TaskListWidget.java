package scrum.client.project.task;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.Composite;

public class TaskListWidget extends Composite {

	public List<Task> taskList = new ArrayList<Task>();

	public BlockListWidget list = new BlockListWidget();

	public TaskListWidget(List<Task> taskList) {
		for (Task task : taskList) {
			list.addBlock(new TaskWidget(task));
		}

		initWidget(list);
	}

}
