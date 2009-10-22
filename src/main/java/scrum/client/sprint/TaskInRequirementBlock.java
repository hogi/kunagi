package scrum.client.sprint;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.tasks.TaskWidget;

import com.google.gwt.user.client.ui.Widget;

public class TaskInRequirementBlock extends ABlockWidget<Task> implements TrashSupport {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Task task = getObject();
		header.addMenuAction(new ClaimTaskAction(task));
		header.addMenuAction(new CloseTaskAction(task));
		header.addMenuAction(new ReopenTaskAction(task));
		header.addMenuAction(new UnclaimTaskAction(task));
		header.addMenuAction(new DeleteTaskAction(task));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Task task = getObject();
		header.setDragHandle(task.getReference());
		header.setCenter(task.getLongLabel(true, false));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new TaskWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteTaskAction(getObject());
	}

	public static final BlockWidgetFactory<Task> FACTORY = new BlockWidgetFactory<Task>() {

		public TaskInRequirementBlock createBlock() {
			return new TaskInRequirementBlock();
		}
	};
}
