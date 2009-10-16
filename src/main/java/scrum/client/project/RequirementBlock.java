package scrum.client.project;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class RequirementBlock extends AExtensibleBlockWidget<Requirement> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		Requirement requirement = getObject();
		setBlockTitle(requirement.getLongLabel());
		String style = null;
		if (requirement.isClosed()) {
			style = "RequirementBlock-closed";
		} else if (requirement.isInCurrentSprint()) {
			style = "RequirementBlock-inCurrentSprint";
		} else if (!requirement.isValidForSprint()) {
			style = "RequirementBlock-invalidForSprint";
		}
		setAdditionalStyleName(style);
		addMenuAction(new AddRequirementToCurrentSprintAction(requirement));
		addMenuAction(new RemoveRequirementFromSprintAction(requirement));
		addMenuAction(new CloseRequirementAction(requirement));
		addMenuAction(new ReopenRequirementAction(requirement));
		addMenuAction(new SetRequirementDirtyAction(requirement));
		addMenuAction(new SetRequirementCleanAction(requirement));
		addMenuAction(new DeleteRequirementAction(requirement));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new RequirementWidget(getObject(), true, true, false, true);
	}

	public Image getClipboardIcon() {
		return Img.bundle.requirement16().createImage();
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
