package scrum.client.tasks;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;
import scrum.client.sprint.CreateTaskAction;

public class RequirementInWhiteboardBlock extends AExtensibleBlockWidget<Requirement> {

	private Requirement requirement;
	private RequirementWidget requirementWidget;

	@Override
	protected Requirement getObject() {
		return requirement;
	}

	@Override
	protected void setObject(Requirement object) {
		this.requirement = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(requirement.getReference() + " " + requirement.getLabel());
		addMenuAction(new CreateTaskAction(requirement));
	}

	@Override
	protected void onExtendedInitialization() {
		requirementWidget = new RequirementWidget(requirement, true, false, true, true);
	}

	@Override
	protected void onUpdateBody() {
		setContent(requirementWidget.update());
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public static BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInWhiteboardBlock createBlock() {
			return new RequirementInWhiteboardBlock();
		}
	};
}
