package scrum.client.tasks;

import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.journal.ActivateChangeHistoryAction;
import scrum.client.project.CloseRequirementAction;
import scrum.client.project.FixRequirementAction;
import scrum.client.project.RejectRequirementAction;
import scrum.client.project.RemoveRequirementFromSprintAction;
import scrum.client.project.ReopenRequirementAction;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;
import scrum.client.sprint.CreateTaskAction;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInWhiteboardBlock extends ABlockWidget<Requirement> {

	private SimplePanel statusIcon;
	private Label statusLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		statusIcon = header.insertPrefixIcon();
		statusLabel = header.appendCenterSuffix("");
		header.appendCell(new EmoticonsWidget(requirement), null, true, true, null);
		header.addMenuAction(new RejectRequirementAction(requirement));
		header.addMenuAction(new FixRequirementAction(requirement));
		header.addMenuAction(new CloseRequirementAction(requirement));
		header.addMenuAction(new ReopenRequirementAction(requirement));
		header.addMenuAction(new RemoveRequirementFromSprintAction(requirement));
		header.addMenuAction(new ActivateChangeHistoryAction(requirement));
		header.addMenuAction(new CreateTaskAction(requirement));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		header.setDragHandle(requirement.getReference());
		Image statusImage = null;
		if (requirement.isRejected()) {
			statusImage = Img.bundle.reqRejected().createImage();
			statusImage.setTitle("Rejected.");
		} else if (requirement.isClosed()) {
			statusImage = Img.bundle.reqClosed().createImage();
			statusImage.setTitle("Accepted.");
		} else if (requirement.isTasksClosed()) {
			statusImage = Img.bundle.reqTasksClosed().createImage();
			statusImage.setTitle("All tasks done.");
		}
		statusIcon.setWidget(statusImage);
		statusLabel.setText(requirement.getTaskStatusLabel());
		header.setCenter(requirement.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new RequirementWidget(getObject(), true, false, true, true, false, true, true);
	}

	public static final BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInWhiteboardBlock createBlock() {
			return new RequirementInWhiteboardBlock();
		}
	};
}
