package scrum.client.sprint;

import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.tasks.TaskWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskInRequirementBlock extends ABlockWidget<Task> implements TrashSupport {

	private SimplePanel statusIcon;
	private Label workLabel;
	private Label ownerLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Task task = getObject();
		statusIcon = header.insertPrefixIcon();
		workLabel = header.appendCenterSuffix("");
		ownerLabel = header.appendCenterSuffix("");
		header.appendCell(new EmoticonsWidget(task), null, true, true, null);
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
			ownerLabel.setText("");
			task.getBurnedWork();
		} else if (task.isOwnerSet()) {
			statusImage = Img.bundle.tskClaimed().createImage();
			statusImage.setTitle("Claimed by " + task.getOwner().getName() + ".");
			ownerLabel.setText(task.getOwner().getName());
		}
		workLabel.setText(task.getWorkText());
		statusIcon.setWidget(statusImage);
		header.setCenter(task.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new TaskWidget(getObject(), false);
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
