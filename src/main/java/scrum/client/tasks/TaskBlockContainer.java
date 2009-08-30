package scrum.client.tasks;

import scrum.client.common.BlockListSelectionManager;
import scrum.client.sprint.Task;

public interface TaskBlockContainer {

	void selectTask(Task task);

	BlockListSelectionManager getSelectionManager();

}
