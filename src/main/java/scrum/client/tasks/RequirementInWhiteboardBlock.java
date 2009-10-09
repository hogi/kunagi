package scrum.client.tasks;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;
import scrum.client.sprint.CreateTaskAction;

import com.google.gwt.user.client.ui.Widget;

public class RequirementInWhiteboardBlock extends AExtensibleBlockWidget<Requirement> {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		Requirement requirement = getObject();
		setBlockTitle(requirement.getReference() + " " + requirement.getLabel());
		addMenuAction(new CreateTaskAction(requirement));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new RequirementWidget(getObject(), true, false, true, true);
	}

	public static BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInWhiteboardBlock createBlock() {
			return new RequirementInWhiteboardBlock();
		}
	};
}
