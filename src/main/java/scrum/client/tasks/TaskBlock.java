package scrum.client.tasks;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.sprint.ClaimTaskAction;
import scrum.client.sprint.CloseTaskAction;
import scrum.client.sprint.DeleteTaskAction;
import scrum.client.sprint.ReopenTaskAction;
import scrum.client.sprint.Task;
import scrum.client.sprint.UnclaimTaskAction;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskBlock extends ABlockWidget<Task> implements TrashSupport {

	private SimplePanel statusIcon;

	private TaskBlockContainer container;

	public TaskBlock(TaskBlockContainer container) {
		this.container = container;
	}

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Task task = getObject();
		statusIcon = header.insertPrefixIcon();
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
		Image statusImage = null;
		if (task.isClosed()) {
			statusImage = Img.bundle.tskClosed().createImage();
			statusImage.setTitle("Closed.");
		} else if (task.isOwnerSet()) {
			statusImage = Img.bundle.tskClaimed().createImage();
			statusImage.setTitle("Claimed by " + task.getOwner().getName() + ".");
		}
		statusIcon.setWidget(statusImage);
		header.setCenter(task.getLongLabel(container.isShowOwner(), container.isShowRequirement()));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new TaskWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteTaskAction(getObject());
	}

	private boolean isTaskOverview() {
		return container instanceof TaskOverviewWidget;
	}

	public TaskBlockContainer getContainer() {
		return this.container;
	}

	public static class TaskBlockFactory implements BlockWidgetFactory<Task> {

		private TaskBlockContainer container;

		public TaskBlockFactory(TaskBlockContainer container) {
			this.container = container;
		}

		public ABlockWidget<Task> createBlock() {
			return new TaskBlock(container);
		}
	}
}
