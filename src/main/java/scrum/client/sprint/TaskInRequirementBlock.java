package scrum.client.sprint;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.tasks.TaskWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class TaskInRequirementBlock extends AExtensibleBlockWidget<Task> implements TrashSupport, ClipboardSupport {

	private Task task;
	private TaskWidget taskWidget;

	@Override
	protected Task getObject() {
		return task;
	}

	@Override
	protected void setObject(Task object) {
		this.task = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.task16());
	}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(task.getLongLabel(true, false));
		setAdditionalStyleName(task.isDone() ? "TaskInRequirementBlock-taskClosed" : null);
		addMenuAction(new ClaimTaskAction(task));
		addMenuAction(new CloseTaskAction(task));
		addMenuAction(new ReopenTaskAction(task));
		addMenuAction(new UnclaimTaskAction(task));
		addMenuAction(new DeleteTaskAction(task));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new TaskWidget(task);
	}

	public Task getTask() {
		return task;
	}

	public Image getClipboardIcon() {
		return Img.bundle.task16().createImage();
	}

	public String getClipboardLabel() {
		return task.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	public AScrumAction getTrashAction() {
		return new DeleteTaskAction(task);
	}

	public static BlockWidgetFactory<Task> FACTORY = new BlockWidgetFactory<Task>() {

		public TaskInRequirementBlock createBlock() {
			return new TaskInRequirementBlock();
		}
	};
}
