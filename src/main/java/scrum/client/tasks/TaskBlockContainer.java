package scrum.client.tasks;

import ilarkesto.gwt.client.Updatable;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.sprint.Task;

public interface TaskBlockContainer {

	void selectTask(Task task);

	Updatable update();

	BlockListSelectionManager getSelectionManager();

	boolean isShowRequirement();

	boolean isShowOwner();

	boolean isWideMode();

}
