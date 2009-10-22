package scrum.client.project;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementBlock extends ABlockWidget<Requirement> implements TrashSupport {

	private SimplePanel statusIcon;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		statusIcon = header.insertPrefixIcon();
		header.addMenuAction(new AddRequirementToCurrentSprintAction(requirement));
		header.addMenuAction(new RemoveRequirementFromSprintAction(requirement));
		header.addMenuAction(new CloseRequirementAction(requirement));
		header.addMenuAction(new ReopenRequirementAction(requirement));
		header.addMenuAction(new SetRequirementDirtyAction(requirement));
		header.addMenuAction(new SetRequirementCleanAction(requirement));
		header.addMenuAction(new DeleteRequirementAction(requirement));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		header.setDragHandle(requirement.getReference());
		Image statusImage = null;
		if (requirement.isInCurrentSprint()) {
			statusImage = Img.bundle.reqInSprint().createImage();
			statusImage.setTitle("In current sprint.");
		} else if (requirement.isClosed()) {
			statusImage = Img.bundle.reqClosed().createImage();
			statusImage.setTitle("Closed.");
		} else if (!requirement.isEstimatedWorkValid()) {
			statusImage = Img.bundle.reqDirty().createImage();
			statusImage.setTitle("Needs estimation.");
		}
		statusIcon.setWidget(statusImage);
		header.setCenter(requirement.getLongLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new RequirementWidget(getObject(), true, true, false, true);
	}

	public AScrumAction getTrashAction() {
		return new DeleteRequirementAction(getObject());
	}

	public static final BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementBlock createBlock() {
			return new RequirementBlock();
		}
	};

}
