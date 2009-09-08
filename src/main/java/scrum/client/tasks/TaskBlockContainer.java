package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.sprint.Task;

public interface TaskBlockContainer {

	void selectTask(Task task);

	AWidget update();

	BlockListSelectionManager getSelectionManager();

}
