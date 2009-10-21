package scrum.client.sprint;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.tasks.TaskWidget;

import com.google.gwt.user.client.ui.Widget;

public class TaskInRequirementBlock extends AExtensibleBlockWidget<Task> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.task16());
	}

	@Override
	protected void onUpdateHead() {
		Task task = getObject();
		setBlockTitle(task.getLongLabel(true, false));
		addMenuAction(new ClaimTaskAction(task));
		addMenuAction(new CloseTaskAction(task));
		addMenuAction(new ReopenTaskAction(task));
		addMenuAction(new UnclaimTaskAction(task));
		addMenuAction(new DeleteTaskAction(task));
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
